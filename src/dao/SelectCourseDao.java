package dao;

import java.util.ArrayList;

import model.StudentCourse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SelectCourseDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> searchcourse(){
		ArrayList<String> courselist = new ArrayList<String>();
		Session session= sessionFactory.openSession();
		String query1="";
		Query query;
		query1="select c.courseName from Course c";
		query=session.createQuery(query1);
		courselist=(ArrayList<String>) query.list();
		session.flush();
		session.clear();
		session.close();
		return courselist;
	}
	
	public boolean tijiaocourse(ArrayList<StudentCourse> sclist){
		Session session=sessionFactory.openSession();
		int count=0;
		for (StudentCourse studentCourse:sclist) {
			count=(Integer) session.save(studentCourse);
		}
		session.flush();
		session.clear();
		session.close();
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
