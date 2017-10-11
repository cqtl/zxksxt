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
	 * 跳转到改卷的页面
	 * @return
	 */
	@RequestMapping
	public String gaijuanpage(){
		return "Gaijuan";
	}
	
	/**
	 * 跳转到考生答案页面
	 */
	@RequestMapping(value="kaoshengpage")
	public String kaoshengpage(String shijuanname, Model model){
		model.addAttribute("shijuanname", shijuanname);
		return "answerforkaosheng";
	}
	
	/**
	 * 跳转到参考答案页面
	 */
	@RequestMapping(value="refrencepage")
	public String refrencepage(String shijuanname, Model model){
		model.addAttribute("shijuanname", shijuanname);
		return "answerforrefrence";
	}
	
	/**
	 * 查询登录教师是否有未批改的试卷
	 */
	@RequestMapping(value="checkgaijuan")
	public void checkgaijuan(HttpServletResponse response ,HttpSession session){
		String jsondata="";
		int userid=(Integer) session.getAttribute("successuserid");
		String username=(String) session.getAttribute("successusername");
		boolean isgaijuan=gaijuanDao.checkgaijuan(userid, username);
		if (isgaijuan) {
			jsondata="{\"status\":true,\"message\":\"有未改试卷\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"你现在没有未改试卷\"}";
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
	 * 查询教师未批改的试卷名称
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
	 * 查询该试卷的参考答案
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
	 * 查询该试卷的考生作答的试卷的答案
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
	 * 查询此试卷所有作答考生的id
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
	 * 查询该试卷考生的总人数
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
	 * 改卷教师提交改卷结果
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
			jsondata="{\"status\":true,\"message\":\"提交成功\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"提交失败\"}";
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
