package action;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Singlesubject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SearchsubjectDao;

@Scope(value = "prototype")
@Controller("SearchsubjectAction")
public class SearchsubjectAction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Autowired
	SearchsubjectDao searchsubjectDao;
	@RequestMapping(value="searchsingle")
	public String searchsingle(Model model){
		ArrayList<Singlesubject>list=new ArrayList<Singlesubject>();
		list=searchsubjectDao.searchsinglesubjectdao();
	
		model.addAttribute("single", list);
		return "showsubject";
	}
	
	
	/**
	 * 查询用户现在是否有考试
	 * @param response
	 */
	@RequestMapping(value="checkexam")
	public void checkexam(HttpServletResponse response ,HttpSession session){
		String jsondata="";
		int userid=(Integer) session.getAttribute("successuserid");
		boolean iscanexam=searchsubjectDao.checkexamdao(userid);
		if (iscanexam) {
			jsondata="{\"status\":true,\"message\":\"祝你考试成功\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"当前没有考试\"}";
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
	 * 判断答案是否正确
	 * @param singlesubject
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="checkanswer")
	public void checkanswer(Singlesubject singlesubject,HttpServletResponse response,HttpServletRequest request){
		ArrayList<Singlesubject>list=new ArrayList<Singlesubject>();
		list=searchsubjectDao.searchanswerdao();
		String answer2[];
		answer2=request.getParameter("name").toString().split("&");
		String answer1=request.getParameter("name").toString();
		System.out.println("answer1:"+answer1);
		
		for (int i = 0,j=0; i < answer2.length; i++,j++) {
			String answer=list.get(j).getS_id()+"="+list.get(j).getAnswer();
			if (answer.equals(answer2[i])) {
				System.out.println("yes");
			}
			else {
				System.out.println("no");
			}
		}
		
	}

}
