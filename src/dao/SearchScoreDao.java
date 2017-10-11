package dao;

import java.util.ArrayList;

import model.Student;
import model.StudentCourse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SearchScoreDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	/**
	 * 查询学生信息
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Student> searchstudentinfo(int userid){
		Session session=sessionFactory.openSession();
		ArrayList<Student> list = new ArrayList<Student>();
		String query1="";
		Query query;
		query1="from Student s where s.userid='"+userid+"'";
		query=session.createQuery(query1);
		list = (ArrayList<Student>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<StudentCourse> searchstuscore(int number){
		Session session=sessionFactory.openSession();
		ArrayList<StudentCourse> list =new ArrayList<StudentCourse>();
		String query1="";
		Query query;
		query1="from StudentCourse sc where sc.number='"+number+"' and sc.ispass='1' ";
		query=session.createQuery(query1);
		list=(ArrayList<StudentCourse>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	/**
	 * 教师查询学生成绩
	 * @param kemu
	 * @param banji
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> teachersearchscore(String kemu,String banji){
		ArrayList<Object[]> scoreList = new ArrayList<Object[]>();
		Session session = sessionFactory.openSession();
		String query1="";
		Query query;
		query1="select sc.number, s.name, sc.course, sc.score from StudentCourse sc, Student s where s.class_='"+banji+"' and sc.course='"+kemu+"' and sc.number=s.number and sc.ispass=1";
		query=session.createQuery(query1);
		scoreList=(ArrayList<Object[]>) query.list();
		session.flush();
		session.clear();
		session.close();
		return scoreList;
	}

}
