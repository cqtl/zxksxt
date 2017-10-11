package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Singlesubject;
import model.Subjects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SearchsubjectDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Singlesubject> searchsinglesubjectdao(){
		Session session=sessionFactory.openSession();
		String query="from Singlesubject";
		ArrayList<Singlesubject>list=new ArrayList<Singlesubject>();
		Query query2=session.createQuery(query);
		list=(ArrayList<Singlesubject>) query2.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Singlesubject> searchanswerdao(){
		Session session=sessionFactory.openSession();
		String query="from Singlesubject";
		ArrayList<Singlesubject>list=new ArrayList<Singlesubject>();
		Query query2=session.createQuery(query);
		list=(ArrayList<Singlesubject>) query2.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkexamdao(int userid){
		boolean ishaveexamination=false;
		Session session =sessionFactory.openSession();
		String query="select e.examtime,e.howlong from Examination e,Student_Course sc,Student s,User u where u.id='"+userid+"' and u.id=s.userid and s.number=sc.number and sc.course=e.examcourse and e.state='1' and e.shijuanname not in (SELECT DISTINCT ss.shijuanname FROM Student_Shijuan ss where ss.user_id='"+userid+"')";
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		Query query2=session.createSQLQuery(query);
		list=(ArrayList<Object[]>) query2.list();
		session.flush();
		session.clear();
		session.close();
		Date currenDate=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Object[] objects:list) {
			Long lasttime= Long.parseLong((objects[1].toString()))*60*1000;
			try {
				if (currenDate.getTime()>sf.parse(objects[0].toString()).getTime()&&currenDate.getTime()<(sf.parse(objects[0].toString()).getTime()+lasttime)) {
					ishaveexamination=true;
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ishaveexamination;
	}
	
	/**
	 * 查询题目信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> searchsubjectsDao(int pagenow,int pagesize,Subjects subject){
		int pagenum=(pagenow-1)*pagesize;
		Session session=sessionFactory.openSession();
		StringBuffer query=new StringBuffer("from Subjects s where 1=1 ");
		if (subject.getAttribute()!=""&&subject.getAttribute()!=null) {
			query.append(" and s.attribute like '%"+subject.getAttribute()+"%'");
		}
		if (subject.getLeixing()!=""&&subject.getLeixing()!=null) {
			query.append(" and s.leixing like '%"+subject.getLeixing()+"%'");
		}
		if (subject.getSubject()!=""&&subject.getSubject()!=null) {
			query.append(" and s.subject like '%"+subject.getSubject()+"%'");
		}
		ArrayList<Object>list=new ArrayList<Object>();
		Query query2=session.createQuery(query.toString());
		query2.setFirstResult(pagenum);  //设置从第几天数据开始查
		query2.setMaxResults(pagesize);  //设置一次查询的条数
		list=(ArrayList<Object>) query2.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	/**
	 * 查询题目的数量
	 * @return
	 */
	public int searchsubjectscount(Subjects subject){
		Long count;
		Session session=sessionFactory.openSession();
		StringBuffer queryString=new StringBuffer("select count(*) from Subjects s where 1=1 ");
		if (subject.getAttribute()!=""&&subject.getAttribute()!=null) {
			queryString.append(" and s.attribute like '%"+subject.getAttribute()+"%'");
		}
		if (subject.getLeixing()!=""&&subject.getLeixing()!=null) {
			queryString.append(" and s.leixing like '%"+subject.getLeixing()+"%'");
		}
		if (subject.getSubject()!=""&&subject.getSubject()!=null) {
			queryString.append(" and s.subject like '%"+subject.getSubject()+"%'");
		}
		Query query=session.createQuery(queryString.toString());
		count= (Long) query.uniqueResult();
		session.flush();
		session.clear();
		session.close();
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> searchsubdetail(String id){
		Session session=sessionFactory.openSession();
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		String querysubject="select sub.subject , sub.attribute , sub.leixing , an , u.name from Subjects sub, Answers an , User u where sub.id='"+id+"' and an.subjectId=sub.id and sub.creatby=u.id";
		Query query =session.createQuery(querysubject.toString());
		list=(ArrayList<Object[]>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}

}
