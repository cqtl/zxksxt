package dao;

import model.Answers;
import model.Subjects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddsubjectsDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public boolean addsubjects(Subjects subject,Answers answer){
		Session session=sessionFactory.openSession();
		session.save(subject);
		answer.setSubjectId(subject.getId());
		session.save(answer);
		session.flush();
		session.clear();
		session.close();
		return true;
	}

}
