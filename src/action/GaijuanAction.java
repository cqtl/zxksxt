package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.GaijuanDao;

@Scope(value="prototype")
@Controller("gaijuan")
@RequestMapping(value="gaijuan")
public class GaijuanAction {
	@Autowired GaijuanDao gaijuanDao;
	
	/**
	 * ��ת���ľ��ҳ��
	 * @return
	 */
	@RequestMapping
	public String gaijuanpage(){
		return "Gaijuan";
	}
	
	/**
	 * ��ת��������ҳ��
	 */
	@RequestMapping(value="kaoshengpage")
	public String kaoshengpage(String shijuanname, Model model){
		model.addAttribute("shijuanname", shijuanname);
		return "answerforkaosheng";
	}
	
	/**
	 * ��ת���ο���ҳ��
	 */
	@RequestMapping(value="refrencepage")
	public String refrencepage(String shijuanname, Model model){
		model.addAttribute("shijuanname", shijuanname);
		return "answerforrefrence";
	}
	
	/**
	 * ��ѯ��¼��ʦ�Ƿ���δ���ĵ��Ծ�
	 */
	@RequestMapping(value="checkgaijuan")
	public void checkgaijuan(HttpServletResponse response ,HttpSession session){
		String jsondata="";
		int userid=(Integer) session.getAttribute("successuserid");
		String username=(String) session.getAttribute("successusername");
		boolean isgaijuan=gaijuanDao.checkgaijuan(userid, username);
		if (isgaijuan) {
			jsondata="{\"status\":true,\"message\":\"��δ���Ծ�\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"������û��δ���Ծ�\"}";
		}
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsondata);
			response.getWriter().close();
			response.getWriter().flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ��ʦδ���ĵ��Ծ�����
	 */
	@RequestMapping(value="searchuncheckshijuan")
	public void searchuncheckshijuan(HttpServletResponse response ,HttpSession session){
		int userid=(Integer) session.getAttribute("successuserid");
		String username=(String) session.getAttribute("successusername");
		ArrayList<Object[]> sList = new ArrayList<Object[]>();
		sList=gaijuanDao.searchunckeckedshijuan(userid, username);
		JSONArray jsonArray=JSONArray.fromObject(sList);
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
	 * ��ѯ���Ծ�Ĳο���
	 * @param shijuanname
	 * @param response
	 */
	@RequestMapping(value="refrence")
	public void answerforrefrence(String shijuanname,HttpServletResponse response){
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		list=gaijuanDao.searchrefrence(shijuanname);
		JSONArray jsonArray=JSONArray.fromObject(list);
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
	 * ��ѯ���Ծ�Ŀ���������Ծ�Ĵ�
	 * @param shijuanname
	 * @param userid
	 * @param response
	 */
	@RequestMapping(value="kaosheng")
	public void answerforkaosheng(String shijuanname,Integer userid,HttpServletResponse response){
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		list=gaijuanDao.searchkaoshenganswer(shijuanname, userid);
		JSONArray jsonArray=JSONArray.fromObject(list);
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
	 * ��ѯ���Ծ�������������id
	 * @param shijuanname
	 * @param response
	 */
	@RequestMapping(value="kaoshengids")
	public void searchkaoshengids(String shijuanname,HttpServletResponse response){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list=gaijuanDao.searchkaoshengid(shijuanname);
		JSONArray jsonArray=JSONArray.fromObject(list);
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
	 * ��ѯ���Ծ�����������
	 * @param shijuanname
	 * @param response
	 */
	@RequestMapping(value="kaoshengnum")
	public void searchkaoshengnum(String shijuanname,HttpServletResponse response){
		String jsondata="";
		int kaoshengnum=0;
		kaoshengnum=gaijuanDao.searchkaoshengnum(shijuanname);
		jsondata="{\"status\":true,\"message\":\""+kaoshengnum+"\"}";
		response.setContentType("text/html;charset=utf-8");
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
	 * �ľ��ʦ�ύ�ľ���
	 * @param kaoshengid
	 * @param response
	 */
	@RequestMapping(value="tijiaogaijuan")
	public void tijiaogaijuan(Integer kaoshengid,String shijuanname,HttpServletResponse response,HttpServletRequest request){
		String jsondata="";
		boolean issuccess1=false;
		boolean issuccess2=false;
		String[] score=request.getParameterValues("score");
		String[] pingyu=request.getParameterValues("pingyu");
		issuccess1=gaijuanDao.tijiaogaijuan(kaoshengid,shijuanname, score, pingyu);
		issuccess2=gaijuanDao.savetostudent(kaoshengid, shijuanname);
		if (issuccess1 && issuccess2) {
			jsondata="{\"status\":true,\"message\":\"�ύ�ɹ�\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"�ύʧ��\"}";
		}
		response.setContentType("text/html;charset=utf-8");
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
