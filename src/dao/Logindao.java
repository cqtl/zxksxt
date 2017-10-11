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
		Query query=session.createQuery(queryString);  //ʹ��hql��ѯ
		//Query query =session.createSQLQuery(sqlquery);//ʹ��sql��ѯ
		list=(ArrayList) query.list();
		session.close();   //����Ҫ�ر����ӣ���Ϊ���Ӵ���ֻ��7�Σ��������ʱ����������7�Σ�(Ҳ���Ǵ�����7��session��ÿ��session����һ������)��ôҳ��ͻ�ȴ�Tomact��Ӧ���Ͳ��ᶯ�ˣ�������Ϊ���ԭ��.
		return list;
		
	}
	
	/**
	 * ��ѯȨ��
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Category> searchCategories(int userid){
		Session session=sessionFactory.openSession();
	//����������Ĳ���Category����	String queryString="SELECT c.module, c.modulename,c.classA,c.classB,c.link FROM Category c, Permission p, Role r, RolePermission rp, User u,UserRole ur WHERE u.id='1' AND u.id=ur.userId AND ur.roleId=r.id AND rp.roleId=r.id AND rp.permissionId=p.id AND p.module= c.module";
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
