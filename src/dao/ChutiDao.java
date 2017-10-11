package dao;

import java.util.ArrayList;

import model.Answers;
import model.Course;
import model.Shijuan;
import model.Subjects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ChutiDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	/**
	 * 查询科目
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Course> searchkemudao(){
		Session session=sessionFactory.openSession();
		String querykemu="from Course";
		ArrayList<Course> list=new ArrayList<Course>();
		Query query=session.createQuery(querykemu);
		list=(ArrayList<Course>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	/**
	 * 查询教师选择的题目
	 * @param subid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Subjects> searchselecttimu(String subid){
		Session session=sessionFactory.openSession();
		String queryselecttimu="from Subjects s where s.id='"+subid+"'";
		ArrayList<Subjects> list=new ArrayList<Subjects>();
		Query query=session.createQuery(queryselecttimu);
		list=(ArrayList<Subjects>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> makesub(String subid,String leixing){
		Session session=sessionFactory.openSession();
		String queryselectsub="select s.id,s.subject from Subjects s where s.id='"+subid+"' and s.leixing='"+leixing+"'";
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		Query query=session.createQuery(queryselectsub);
		list=(ArrayList<Object[]>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Answers> makeans(String subid){
		Session session=sessionFactory.openSession();
		String queryselectans="select a from Answers a where a.subjectId='"+subid+"'";
		ArrayList<Answers> list=new ArrayList<Answers>();
		Query query=session.createQuery(queryselectans);
		list=(ArrayList<Answers>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	public boolean savetoshijuan(Shijuan shijuan){
		Session session=sessionFactory.openSession();
		Query query;
		String hql="";
		Transaction transaction=session.beginTransaction();
		session.save(shijuan);
		hql="update Examination e set e.state=1 where e.shijuanname='"+shijuan.getName()+"'";
		query=session.createQuery(hql);
		query.executeUpdate();
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		return true;
	}
	
	/**
	 * 查询教师所出试卷的名称
	 * @param ctrname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> searchshijuanname(String ctrname){
		Session session=sessionFactory.openSession();
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		String queryshijuanname="select e.shijuanname from Examination e where e.ctrName='"+ctrname+"' and e.state='0'";
		Query query=session.createQuery(queryshijuanname);
		list=(ArrayList<Object[]>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
}
