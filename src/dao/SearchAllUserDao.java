package dao;

import java.util.ArrayList;
import java.util.Iterator;

import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SearchAllUserDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> searchalluserdao(){

		Session session=sessionFactory.openSession();

		String queryString="from User";

		ArrayList<User> list=new ArrayList<User>();
		Query query=session.createQuery(queryString);
		

		list=(ArrayList<User>) query.list();
		System.out.println("fffffff"+list.size());
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> searchfenyeuserdao(int pagenow,int pagesize){
		int pagenum=(pagenow-1)*pagesize;
		
		Session session=sessionFactory.openSession();
		
		String queryString="from User";
		
		ArrayList<User> list=new ArrayList<User>();
		Query query=session.createQuery(queryString);
		//分页查询
		
		query.setFirstResult(pagenum);    //从第几条开始查询
		query.setMaxResults(pagesize);     //查询多少条数据

		list=(ArrayList<User>) query.list();
		System.out.println("fffffff"+list.size());
		session.flush();
		session.clear();
		session.close();
		return list;
	}
	
	public int searchcount(){
		Long count;
		Session session=sessionFactory.openSession();
		String sql="select count(*) from User";
		Query query=session.createQuery(sql);
		count=(Long) query.uniqueResult();  //因为返回的是long数据类型所以要转换
		System.out.println(count);
		session.flush();
		session.clear();
		session.close();
		return count.intValue();
	}
	
	
	

}
