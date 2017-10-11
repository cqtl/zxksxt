package dao;

import java.util.ArrayList;

import model.Examination;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GaijuanDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> searchrefrence(String shijuanname){
		String subids[];
		Session session=sessionFactory.openSession();
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		String query1="";
		Query query;
		query1="select s.subjectIds, s.score from Shijuan s where s.name='"+shijuanname+"'";
		query=session.createQuery(query1);
		ArrayList<Object[]> sList = new ArrayList<Object[]>();
		sList=(ArrayList<Object[]>) query.list();
		String subidString=(String) sList.get(0)[0];
		subids=subidString.split(",");
		String score=(String) sList.get(0)[1];
		String scores[]=score.split(",");
		for (int i = 0; i < subids.length; i++) {
			query1="select s.subject,a.answer from Subjects s,Answers a where s.id='"+subids[i]+"' and s.id=a.subjectId and s.leixing!='单选题' and s.leixing!='多选题' ";
			query=session.createQuery(query1);
			Object[] objects=new Object[3];
			if (query.list().size()!=0) {
				ArrayList<Object[]> tlist = new ArrayList<Object[]>();
				tlist=(ArrayList<Object[]>) query.list();
				objects[0]=tlist.get(0)[0];
				objects[1]=tlist.get(0)[1];
				objects[2]=scores[i];
				list.add(objects);
			}
		}
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> searchkaoshenganswer(String shijuanname,Integer userid){
		String subids[];
		Session session=sessionFactory.openSession();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String query1="";
		Query query;
		query1="select s.subjectIds from Shijuan s where s.name='"+shijuanname+"'";
		query=session.createQuery(query1);
		String subidString=(String) query.list().get(0);
		subids=subidString.split(",");
		for (int i = 0; i < subids.length; i++) {
			query1="select s.subject,ss.answer from Subjects s,StudentShijuan ss where s.id='"+subids[i]+"' and ss.userId='"+userid+"' and ss.shijuanname='"+shijuanname+"' and s.id=ss.subjectId and ss.ischecked=0 ";
			query=session.createQuery(query1);
			list.addAll(query.list());
		}
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> searchkaoshengid(String shijuanname){
		Session session=sessionFactory.openSession();
		ArrayList<Integer> list = new ArrayList<Integer>();
		String subids[];
		Query query;
		String queryString="";
		queryString="select s.subjectIds from Shijuan s where s.name='"+shijuanname+"'";
		query=session.createQuery(queryString);
		String subidString=(String) query.list().get(0);
		subids=subidString.split(",");

		queryString="select distinct ss1.userId from StudentShijuan ss1 where ss1.ischecked=0 and ss1.shijuanname='"+shijuanname+"' and ss1.userId in( select distinct ss.userId from StudentShijuan ss where ss.shijuanname='"+shijuanname+"' and ss.subjectId='"+subids[0]+"')";
		query=session.createQuery(queryString);
		list.addAll(query.list());
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	public int searchkaoshengnum(String shijuanname){
		Session session=sessionFactory.openSession();
		int kaoshengnum=0;
		Query query;
		String queryString="";
		queryString="select count(*) from (select distinct ss.user_Id from Student_Shijuan ss where ss.shijuanname='"+shijuanname+"') t ";
		query=session.createSQLQuery(queryString);
		kaoshengnum=Integer.parseInt(query.uniqueResult().toString());
		session.flush();
		session.clear();
		session.close();
		return kaoshengnum;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean tijiaogaijuan(Integer kaoshengid,String shijuanname,String score[],String pingyu[]){
		Session session=sessionFactory.openSession();
		Query query;
		String queryString="";
		int rowcount=0;
		queryString="select ss.subjectId from StudentShijuan ss where ss.shijuanname='"+shijuanname+"' and ss.ischecked=0 and ss.userId='"+kaoshengid+"'";
		query=session.createQuery(queryString);
		ArrayList<Integer> listsubjects=(ArrayList<Integer>) query.list();
		
		for (int i = 0; i < listsubjects.size(); i++) {
			queryString="update StudentShijuan ss set ss.ischecked=1,ss.score=?,ss.pingyu=? where ss.shijuanname='"+shijuanname+"' and ss.userId='"+kaoshengid+"' and ss.subjectId='"+listsubjects.get(i)+"'";
			query=session.createQuery(queryString);
			query.setString(0, score[i]);
			query.setString(1, pingyu[i]);
			rowcount=query.executeUpdate();
		}
		session.flush();
		session.clear();
		session.close();
		if (rowcount<1) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean savetostudent(Integer kaoshengid,String shijuanname){
		Session session=sessionFactory.openSession();
		Query query;
		String queryString="";
		Float score=(float) 0.0;
		ArrayList<Float> listscore=new ArrayList<Float>();
		queryString="select ss.score from StudentShijuan ss where ss.userId='"+kaoshengid+"' and ss.shijuanname='"+shijuanname+"' ";
		query=session.createQuery(queryString);
		listscore=(ArrayList<Float>) query.list();
		for (int i = 0; i < listscore.size(); i++) {
			score=score+listscore.get(i);
		}
		queryString="update Student_Course sc set sc.score=? , sc.ispass=1 where sc.ispass=0  and sc.number in (select s.number from Student s where s.userId='"+kaoshengid+"') and sc.course in (select e.examcourse from Examination e where e.shijuanname='"+shijuanname+"')";
		query=session.createSQLQuery(queryString);
		query.setFloat(0, score);
		int rowcount=0;
		rowcount=query.executeUpdate();
		session.flush();
		session.clear();
		session.close();
		if (rowcount<1) {
			return false;
		}
		return true;
	}
	
	/**
	 * 查询登录教师是否有未改试卷
	 */
	public boolean checkgaijuan(int userid, String username){
		boolean ishavauncheckshijuan=false;
		Session session = sessionFactory.openSession();
		Query query;
		String hql="";
		hql="select count(*) from Student_Shijuan ss where ss.shijuanname in (select e.shijuanname from Examination e where e.gjr_Name='"+username+"') and ss.ischecked=0 ";
		query=session.createSQLQuery(hql);
		int count=Integer.parseInt(query.uniqueResult().toString());
		if (count>0) {
			ishavauncheckshijuan=true;
		}
		else {
			ishavauncheckshijuan=false;
		}
		session.flush();
		session.clear();
		session.close();
		return ishavauncheckshijuan;
	}
	
	/**
	 * 查询教师未改试卷的名称
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> searchunckeckedshijuan(int userid, String username){
		ArrayList<Object[]> sList = new ArrayList<Object[]>();
		Session session=sessionFactory.openSession();
		Query query;
		String hql="";
		hql="select distinct ss.shijuanname from Student_Shijuan ss where ss.shijuanname in (select e.shijuanname from Examination e where e.gjr_Name='"+username+"') and ss.ischecked=0 ";
		query=session.createSQLQuery(hql);
		sList=(ArrayList<Object[]>) query.list();
		session.flush();
		session.clear();
		session.close();
		return sList;
	}

}
