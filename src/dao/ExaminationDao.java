package dao;

import java.util.ArrayList;

import model.Examination;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ExaminationDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	/**
	 * 查询出题人和改卷人
	 * @param kemu
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<User> searchname(String kemu,String name){
		ArrayList<User> list=new ArrayList<User>();
		Session session=sessionFactory.openSession();
		String queryuser="SELECT u FROM User u, UserRole ur, TeacherCourse tc,Role r WHERE r.name='"+name+"' AND  r.id=ur.roleId AND ur.userId=u.id AND tc.teacherid=u.id AND tc.course='"+kemu+"'";
		Query query=session.createQuery(queryuser);
		list=(ArrayList<User>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	/**
	 * 保存考试
	 * @param examination
	 * @return
	 */
	public boolean saveexam(Examination examination){
		Session session=sessionFactory.openSession();
		session.save(examination);
		session.flush();
		session.clear();
		session.close();
		return true;
	}
	
	/**
	 * 查询未考的考试
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Examination> searchunexamination(){
		ArrayList<Examination> examlist = new ArrayList<Examination>();
		String query1="";
		Query query;
		Session session=sessionFactory.openSession();
		query1="select e from Examination e where e.state=0";
		query=session.createQuery(query1);
		examlist=(ArrayList<Examination>) query.list();
		session.flush();
		session.clear();
		session.close();
		return examlist;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Examination> searchunexamination(int examid){
		ArrayList<Examination> examlist = new ArrayList<Examination>();
		String query1="";
		Query query;
		Session session=sessionFactory.openSession();
		query1="select e from Examination e where e.id='"+examid+"'";
		query=session.createQuery(query1);
		examlist=(ArrayList<Examination>) query.list();
		session.flush();
		session.clear();
		session.close();
		return examlist;
	}

}
