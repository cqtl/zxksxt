package dao;

import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistDao {
	@Autowired
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public boolean registDao(User user){
		Session session=sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.clear();
		session.close();
		return true;
		
	}
	

}
