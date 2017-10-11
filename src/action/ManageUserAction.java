package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Permission;
import model.Role;
import model.User;
import net.sf.json.JSONArray;

import oracle.net.aso.p;
import oracle.net.aso.r;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.net.www.content.text.plain;

import dao.ManageUserDao;

@Scope(value="prototype")
@Controller("ManageUser")
@RequestMapping(value="manageuser")
public class ManageUserAction {
	@Autowired
	ManageUserDao manageUserDao;
	
	/**
	 * ��ѯ����Ȩ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="searchpermission")
	public String searchpermission(Model model){
		ArrayList<Permission> pList = new ArrayList<Permission>();
		pList=manageUserDao.searchpermission();
		model.addAttribute("plist",pList);
		return "permission";
	}
	
	/**
	 * ��ѯ���н�ɫ
	 */
	@RequestMapping(value="searchrole")
	public String searchrole(Model model){
		ArrayList<Role> rList = new ArrayList<Role>();
		rList=manageUserDao.searchrole();
		model.addAttribute("rlist", rList);
		return "role";
	}
	/**
	 * ��ѯ�����û�
	 */
	@RequestMapping(value="searchuser")
	public String searchuser(){
		
		return "user";
	}
	
	/**
	 * ��ҳ��ѯ�û���Ϣ
	 */
	@RequestMapping(value="searchfenyeuser")
	public void searchfenyeuser(HttpServletResponse response, HttpServletRequest request){
		int pagenow=0;      //��ǰҳ
		int pagenumber=0;   //�ܹ��ж���ҳ
		String username ="";
		int pagesize=5;        //ÿҳ��ʾ������
		int count=0;         //�û���������
		pagenow=Integer.parseInt(request.getParameter("pagenow"));
		username=request.getParameter("username");
		count=manageUserDao.searchusercount(username);
		pagenumber=count/pagesize;
		if(count%pagesize>0){
			pagenumber=pagenumber+1;
		}
		ArrayList<User> uList = new ArrayList<User>();
		uList=manageUserDao.searchuser(pagenow, pagesize, username);
		JSONArray jsonArray=JSONArray.fromObject(uList);
		jsonArray.add("{\"pagenumber\":\""+pagenumber+"\"}");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ��ɫ����Ӧ��Ȩ��
	 */
	@RequestMapping(value="searchrolepermission")
	public void searchrolepermission(String jsid, HttpServletResponse response){
		ArrayList<Permission> pList = new ArrayList<Permission>();
		pList=(ArrayList<Permission>) manageUserDao.searchrolepermission(Integer.parseInt(jsid));
		JSONArray jsonArray=JSONArray.fromObject(pList);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ�û�����Ӧ�Ľ�ɫ
	 */
	@RequestMapping(value="searchuserrole")
	public void searchuserrole(String yhid, HttpServletResponse response){
		ArrayList<Role> rList = new ArrayList<Role>();
		rList=manageUserDao.searchuserrole(Integer.parseInt(yhid));
		JSONArray jsonArray=JSONArray.fromObject(rList);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ת������Ȩ��ҳ��
	 * @return
	 */
	@RequestMapping(value="addpermissionpage")
	public String addpermissionpage(Model model){
		ArrayList<Role> rlist = new ArrayList<Role>();
		rlist=manageUserDao.searchrole();
		model.addAttribute("rlist", rlist);
		return "addpermission";
	}
	
	/**
	 * ��ת��������ɫҳ��
	 */
	@RequestMapping(value="addrolepage")
	public String addrolepage(){
		return "addrole";
	}
	
	/**
	 * 
	 * ����Ȩ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="addpermission")
	public void addpermission(HttpServletResponse response,HttpServletRequest request){
		String jsondata="";
		boolean issuccess=false;
		String qxmc=request.getParameter("qxmc");
		String qxbs=request.getParameter("qxbs");
		String role=request.getParameter("role");
		Permission permission=new Permission();
		permission.setName(qxmc);
		permission.setModule(qxbs);
		issuccess=manageUserDao.addpermission(permission, role);
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"�����ɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"����ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ������ɫ
	 */
	@RequestMapping(value="addrole")
	public void addrole(HttpServletRequest request, HttpServletResponse response){
		String jsondata="";
		boolean issuccess=false;
		String jsmc=request.getParameter("jsmc");
		String jsbs=request.getParameter("jsbs");
		Role role = new Role();
		role.setName(jsbs);
		role.setChinesename(jsmc);
		issuccess=manageUserDao.addrole(role);
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"�����ɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"����ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ��Ȩ��
	 * @param response
	 * @param permissionid
	 */
	@RequestMapping(value="deletepermission")
	public void deletepermission(HttpServletResponse response,String permissionid){
		String jsondata="";
		boolean issuccess=manageUserDao.deletepermission(Integer.parseInt(permissionid));
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"ɾ���ɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"ɾ��ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ����ɫ
	 */
	@RequestMapping(value="deleterole")
	public void deleterole(String roleid, HttpServletResponse response){
		String jsondata="";
		boolean issuccess=manageUserDao.deleterole(Integer.parseInt(roleid));
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"ɾ���ɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"ɾ��ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ���û�
	 */
	@RequestMapping(value="deleteuser")
	public void deleteuser(String userid, HttpServletResponse response){
		String jsondata="";
		boolean issuccess=manageUserDao.deleteuser(Integer.parseInt(userid));
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"ɾ���ɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"ɾ��ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �༭Ȩ��ҳ����ת
	 * @param permissionid
	 * @return
	 */
	@RequestMapping(value="editpermissionpage")
	public String editpermissionpage(String permissionid,Model model){
		ArrayList<Permission> pList = new ArrayList<Permission>();
		pList=manageUserDao.searchpermissionid(Integer.parseInt(permissionid));
		model.addAttribute("plist", pList);
		return "editpermission";
	}
	
	/**
	 * �༭��ɫҳ����ת
	 */
	@RequestMapping(value="editrolepage")
	public String editrolepage(String roleid, Model model){
		ArrayList<Role> rlList = new ArrayList<Role>();
		ArrayList<Permission> pList = new ArrayList<Permission>();
		rlList=manageUserDao.searchroleid(Integer.parseInt(roleid));
		pList=manageUserDao.searchpermission();
		model.addAttribute("rlist", rlList);
		model.addAttribute("plist", pList);
		return "editrole";
	}
	
	/**
	 * �༭�û�ҳ����ת
	 */
	@RequestMapping(value="edituserpage")
	public String edituserpage(String userid, Model model){
		ArrayList<User> uList = new ArrayList<User>();
		ArrayList<Role> rList = new ArrayList<Role>();
		uList=manageUserDao.searchuserid(Integer.parseInt(userid));
		rList=manageUserDao.searchrole();
		model.addAttribute("ulist", uList);
		model.addAttribute("rlist", rList);
		return "edituser";
	}
	
	/**
	 * �༭Ȩ��
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="editpermission")
	public void editpermission(HttpServletResponse response,HttpServletRequest request){
		String jsondata="";
		boolean issuccess=false;
		String qxmc=request.getParameter("qxmc");
		String qxbs=request.getParameter("qxbs");
		String qxid = request.getParameter("qxid");
		issuccess=manageUserDao.editpermission(qxmc, qxbs, Integer.parseInt(qxid));
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"�޸ĳɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"�޸�ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �༭��ɫ
	 */
	@RequestMapping(value="editrole")
	public void edtirole(HttpServletRequest request, HttpServletResponse response){
		String jsondata="";
		boolean issuccess=false;
		String jsmc=request.getParameter("jsmc");
		String jsbs=request.getParameter("jsbs");
		String jsid = request.getParameter("jsid");
		String qxmc = request.getParameter("qxmc");
		issuccess=manageUserDao.editrole(jsmc, jsbs, qxmc, Integer.parseInt(jsid));
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"�޸ĳɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"�޸�ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �༭�û�
	 */
	@RequestMapping(value="edituser")
	public void edituser(HttpServletResponse response, HttpServletRequest request){
		String jsondata="";
		boolean issuccess=false;
		String yhid=request.getParameter("yhid");
		String yhmc=request.getParameter("yhmc");
		String yhbs=request.getParameter("yhbs");
		String jsmc=request.getParameter("jsmc");
		issuccess=manageUserDao.edituser(yhmc, yhbs, jsmc, Integer.parseInt(yhid));
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"�޸ĳɹ�\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"�޸�ʧ��\"}";
		}
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
