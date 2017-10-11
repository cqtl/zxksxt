package dao;

import java.util.ArrayList;

import model.Category;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Logindao{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public ArrayList logindao(){
		Session session=sessionFactory.openSession();
		String queryString="from User";
		//ArrayList list= (ArrayList) getHibernateTemplate().find(queryString);
		ArrayList list=new ArrayList<User>();
		//list= (ArrayList) getHibernateTemplate().find(queryString);
		Query query=session.createQuery(queryString);  //使用hql查询
		//Query query =session.createSQLQuery(sqlquery);//使用sql查询
		list=(ArrayList) query.list();
		session.close();   //必须要关闭连接，因为连接次数只有7次，在请求的时候如果点击了7次，(也就是创建了7个session，每个session都有一个连接)那么页面就会等待Tomact响应，就不会动了，就是因为这个原因.
		return list;
		
	}
	
	/**
	 * 查询权限
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Category> searchCategories(int userid){
		Session session=sessionFactory.openSession();
	//这样查出来的不是Category对象	String queryString="SELECT c.module, c.modulename,c.classA,c.classB,c.link FROM Category c, Permission p, Role r, RolePermission rp, User u,UserRole ur WHERE u.id='1' AND u.id=ur.userId AND ur.roleId=r.id AND rp.roleId=r.id AND rp.permissionId=p.id AND p.module= c.module";
		String queryString ="select c from Category c, Permission p, Role r, RolePermission rp, User u,UserRole ur WHERE u.id='"+userid+"' AND u.id=ur.userId AND ur.roleId=r.id AND rp.roleId=r.id AND rp.permissionId=p.id AND p.module= c.module";
		ArrayList<Category> list=new ArrayList<Category>();
		Query query=session.createQuery(queryString);
		list=(ArrayList<Category>) query.list();
		session.flush();
		session.clear();
		session.close();
		return list;
	}

}
