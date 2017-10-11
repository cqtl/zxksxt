package dao;

import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import model.Permission;
import model.Role;
import model.RolePermission;
import model.User;
import model.UserRole;

import oracle.net.aso.r;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import sun.net.www.content.text.plain;

public class ManageUserDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Permission> searchpermission(){
		ArrayList<Permission> plist = new ArrayList<Permission>();
		Session session = sessionFactory.openSession();
		Query query;
		String query1="";
		query1="from Permission";
		query=session.createQuery(query1);
		plist=(ArrayList<Permission>) query.list();
		session.flush();
		session.clear();
		session.close();
		return plist;
	}
	
	
	/**
	 * ��id��ѯȨ��
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Permission> searchpermissionid(Integer permissionid){
		ArrayList<Permission> plist = new ArrayList<Permission>();
		Session session = sessionFactory.openSession();
		Query query;
		String query1="";
		query1="select p from Permission p where p.id='"+permissionid+"'";
		query=session.createQuery(query1);
		plist=(ArrayList<Permission>) query.list();
		session.flush();
		session.clear();
		session.close();
		return plist;
	}
	
	/**
	 * ��ѯ��ɫӵ�е�Ȩ��
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Permission> searchrolepermission(Integer jsid){
		ArrayList<Permission> pList = new ArrayList<Permission>();
		Session session= sessionFactory.openSession();
		Query query;
		String hql="";
		hql="select p from Permission p , RolePermission rp where p.id=rp.permissionId and rp.roleId='"+jsid+"'";
		query=session.createQuery(hql);
		pList=(ArrayList<Permission>) query.list();
		session.flush();
		session.clear();
		session.close();
		return pList;
	}
	
	/**
	 * ��ѯ�û�ӵ�еĽ�ɫ
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Role> searchuserrole(int yhid){
		ArrayList<Role> rList = new ArrayList<Role>();
		Session session = sessionFactory.openSession();
		Query query;
		String hql="";
		hql="select r from Role r , UserRole ur where r.id=ur.roleId and ur.userId='"+yhid+"'";
		query =session.createQuery(hql);
		rList=(ArrayList<Role>) query.list();
		session.flush();
		session.clear();
		session.close();
		return rList;
	}
	
	/**
	 * ��id��ѯ��ɫ
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Role> searchroleid(Integer roleid){
		ArrayList<Role> rList = new ArrayList<Role>();
		Session session = sessionFactory.openSession();
		Query query;
		String hql="";
		hql="select r from Role r where r.id='"+roleid+"'";
		query=session.createQuery(hql);
		rList=(ArrayList<Role>) query.list();
		session.flush();
		session.clear();
		session.close();
		return rList;
	}
	
	/**
	 * ��ѯ���еĽ�ɫ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Role> searchrole(){
		ArrayList<Role> rList = new ArrayList<Role>();
		Session session=sessionFactory.openSession();
		Query query;
		String query1="";
		query1="from Role";
		query=session.createQuery(query1);
		rList=(ArrayList<Role>) query.list();
		session.flush();
		session.clear();
		session.close();
		return rList;
	}
	
	/**
	 * ��ѯ�û���������
	 */
	public int searchusercount(String username){
		Long count;
		Session session = sessionFactory.openSession();
		Query query;
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from User u where 1=1 ");
		if (username!=""&&username!=null) {
			hql.append("and u.name like '%"+username+"%'");
		}
		query=session.createQuery(hql.toString());
		count=(Long) query.uniqueResult();
		session.flush();
		session.clear();
		session.close();
		return count.intValue();
	}
	
	/**
	 * ��ѯ�û���Ϣ�����÷�ҳ��ѯ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<User> searchuser(int pagenow, int pagesize, String username){
		int pagenum=(pagenow-1)*pagesize;
		ArrayList<User> uList = new ArrayList<User>();
		Session session = sessionFactory.openSession();
		Query query;
		StringBuffer hql = new StringBuffer();
		hql.append("select u from User u where 1=1 ");
		if (username!=""&&username!=null) {
			hql.append("and u.name like '%"+username+"%'");
		}
		query=session.createQuery(hql.toString());
		query.setFirstResult(pagenum);  //���ôӵڼ������ݿ�ʼ��
		query.setMaxResults(pagesize);  //����һ�β�ѯ������
		uList=(ArrayList<User>) query.list();
		session.flush();
		session.clear();
		session.close();
		return uList;
	}
	
	/**
	 * ��id��ѯ�û�
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<User> searchuserid(Integer userid){
		ArrayList<User> uList = new ArrayList<User>();
		Session session = sessionFactory.openSession();
		Query query;
		String hql="";
		hql="select u from User u where u.id='"+userid+"'";
		query=session.createQuery(hql);
		uList=(ArrayList<User>) query.list();
		session.flush();
		session.clear();
		session.close();
		return uList;
	}
	
	/**
	 * ����Ȩ��
	 * @param permission
	 * @param role
	 * @return
	 */
	public boolean addpermission(Permission permission, String role){
		Session session = sessionFactory.openSession();
		int rolepermissionnum=0;
		int permissionnum=0;
		permissionnum=(Integer) session.save(permission);
		int permissionid=0;
		int roleid=0;
		Query query;
		String query1="";
		query1="select p.id from Permission p where p.name='"+permission.getName()+"'";
		query=session.createQuery(query1);
		permissionid=(Integer) query.uniqueResult();
		query1="select r.id from Role r where r.name='"+role+"'";
		query=session.createQuery(query1);
		roleid=(Integer) query.uniqueResult();
		RolePermission rolePermission = new RolePermission();
		rolePermission.setPermissionId(permissionid);
		rolePermission.setRoleId(roleid);
		rolepermissionnum=(Integer) session.save(rolePermission);
		session.flush();
		session.clear();
		session.close();
		if (rolepermissionnum>0&&permissionnum>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * ������ɫ
	 */
	public boolean addrole(Role role){
		Session session = sessionFactory.openSession();
		int rolenum=0;
		Transaction transaction = session.beginTransaction();
		rolenum=(Integer) session.save(role);
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		if (rolenum>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * ɾ��Ȩ��
	 */
	public boolean deletepermission(Integer permissionid){
		boolean issuccess=false;
		Session session = sessionFactory.openSession();
		Query query;
		Query query1;
		String hql1="";
		String hql2="";
		Transaction tran = session.beginTransaction();    
		hql1 = "Delete FROM Permission p Where p.id=?" ;     
        query = session.createQuery(hql1) ;     
        query.setInteger(0, permissionid) ;     
        query.executeUpdate() ; 
        hql2="delete from RolePermission rp where rp.permissionId='"+permissionid+"'";
        query1=session.createQuery(hql2);
        query1.executeUpdate();
        issuccess =true;
        tran.commit() ; 
        session.flush();
        session.clear();
        session.close();
        return issuccess;
	}
	
	/**
	 * ɾ����ɫ
	 */
	public boolean deleterole(Integer roleid){
		boolean issuccess=false;
		Session session= sessionFactory.openSession();
		Query query1;
		Query query2;
		Query query3;
		String hql1="";
		String hql2="";
		String hql3="";
		Transaction transaction = session.beginTransaction();
		hql1="delete from Role r where r.id='"+roleid+"'";          //ɾ����ɫ���еĸý�ɫ
		hql2="delete from RolePermission rp where rp.roleId='"+roleid+"'";    //ɾ����ɫȨ�ޱ�
		hql3="delete from UserRole ur where ur.roleId='"+roleid+"'";         //ɾ���û���ɫ��
		query1=session.createQuery(hql1);
		query2=session.createQuery(hql2);
		query3=session.createQuery(hql3);
		query1.executeUpdate();
		query2.executeUpdate();
		query3.executeUpdate();
		issuccess=true;
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		return issuccess;
	}
	
	/**
	 * ɾ���û�
	 */
	public boolean deleteuser(Integer userid){
		boolean issuccess=false;
		Session session = sessionFactory.openSession();
		Query query1;
		Query query2;
		String hql1="";
		String hql2="";
		Transaction transaction = session.beginTransaction();
		hql1="delete from User u where u.id='"+userid+"'";
		hql2="delete from UserRole ur where ur.userId='"+userid+"'";
		query1 = session.createQuery(hql1);
		query2=session.createQuery(hql2);
		query1.executeUpdate();
		query2.executeUpdate();
		issuccess=true;
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		return issuccess;
	}
	
	/**
	 * �༭Ȩ��
	 */
	public boolean editpermission(String qxmc,String qxbs, Integer permissionid){
		int  issuccess=0;
		Session session = sessionFactory.openSession();
		Query query;
		String query1="";
		Transaction transaction = session.beginTransaction();
		query1="update Permission p set p.name=?, p.module=? where p.id=?";
		query=session.createQuery(query1);
		query.setString(0, qxmc);
		query.setString(1, qxbs);
		query.setInteger(2, permissionid);
		issuccess=query.executeUpdate();
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		if (issuccess>0) {
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * �༭��ɫ
	 */
	public boolean editrole(String jsmc,String jsbs, String qxmc, int roleid){
		int  issuccess=0;
		String qxmcs[]=qxmc.split(",");
		Integer qxids[]= new Integer[qxmcs.length];
		Session session = sessionFactory.openSession();
		Query query1;
		String hql1="";
		String hql2="";
		String hql3="";
		Transaction transaction = session.beginTransaction();
		//��ѯ�ý�ɫ�޸ĺ��Ȩ�ޣ�����֮ǰ��Ȩ�޴ӽ�ɫȨ�ޱ���ɾ��
		hql2="delete from RolePermission rp where rp.roleId='"+roleid+"'";
		query1=session.createQuery(hql2);
		query1.executeUpdate();
		if (qxmc!=""&&qxmc!=null) {
			for (int i=0;i<qxmcs.length;i++) {
				hql3="select p.id from Permission p where p.name='"+qxmcs[i]+"'";
				query1=session.createQuery(hql3);
				qxids[i]=(Integer) query1.list().get(0);
				
			}
			//���޸ĺ��Ȩ�޲����ɫȨ�ޱ���
			for (int i = 0; i < qxids.length; i++) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setPermissionId(qxids[i]);
				rolePermission.setRoleId(roleid);
				session.save(rolePermission);
			}
		}
		hql1="update Role r set r.name=?, r.chinesename=? where r.id=?";
		
		query1=session.createQuery(hql1);
		query1.setString(0, jsbs);
		query1.setString(1, jsmc);
		query1.setInteger(2, roleid);
		issuccess=query1.executeUpdate();
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		if (issuccess>0) {
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * �༭�û�
	 */
	public boolean edituser(String yhmc, String yhbs, String jsmc, Integer yhid){
		int  issuccess=0;
		String jsmcs[]=jsmc.split(",");
		Integer jsids[]= new Integer[jsmcs.length];
		Session session = sessionFactory.openSession();
		Query query1;
		String hql1="";
		String hql2="";
		String hql3="";
		Transaction transaction = session.beginTransaction();
		
		//��ѯ���û��޸ĺ�Ľ�ɫ������֮ǰ�Ľ�ɫ���û���ɫ����ɾ��
		hql2="delete from UserRole ur where ur.userId='"+yhid+"'";
		query1=session.createQuery(hql2);
		query1.executeUpdate();
		
		if (jsmc!=""&&jsmc!=null) {
			for (int i=0;i<jsmcs.length;i++) {
				hql3="select r.id from Role r where r.chinesename='"+jsmcs[i]+"'";
				query1=session.createQuery(hql3);
				jsids[i]=(Integer) query1.list().get(0);
			}
			//���޸ĺ��Ȩ�޲����ɫȨ�ޱ���
			for (int i = 0; i < jsids.length; i++) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(jsids[i]);
				userRole.setUserId(yhid);
				session.save(userRole);
			}
		}
		
		hql1="update User u set u.name=?, u.account=? where u.id=?";
		query1=session.createQuery(hql1);
		query1.setString(0, yhmc);
		query1.setString(1, yhbs);
		query1.setInteger(2, yhid);
		issuccess=query1.executeUpdate();
		transaction.commit();
		session.flush();
		session.clear();
		session.close();
		if (issuccess>0) {
			return true;
		}
		else{
			return false;
		}
	}

}
