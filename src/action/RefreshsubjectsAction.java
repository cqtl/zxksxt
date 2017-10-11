package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Answers;
import model.Subjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AddsubjectsDao;

@Scope(value="prototype")
@Controller("RefreshsubjectsAction")
@RequestMapping(value="refreshsubjects")
public class RefreshsubjectsAction {
	@Autowired
	AddsubjectsDao addsubjectsDao;
	
	@RequestMapping(value="/addsubjectspage")
	public String addsubjectspage(){
		return "Refreshsubjects";
	}
	
	/**
	 * 向题库中新增题目
	 * @param answer
	 * @param subject
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/addsubjects")
	public void addsubjects(Answers answer,Subjects subject,HttpServletResponse response,HttpSession session){
		String jsondata="";
		//得到当前登录人的id,即创建人
		int userid=(Integer) session.getAttribute("successuserid");
		subject.setCreatby(userid);
		if (subject.getLeixing().equals("tkt")) {
			subject.setLeixing("填空题");
		}
		if (subject.getLeixing().equals("single")) {
			subject.setLeixing("单选题");
		}
		if (subject.getLeixing().equals("many")) {
			subject.setLeixing("多选题");
		}
		if (subject.getLeixing().equals("jdt")) {
			subject.setLeixing("简答题");
		}
		answer.setSubjectId(subject.getId());
		boolean issuccess=addsubjectsDao.addsubjects(subject, answer);
		if (issuccess) {
			jsondata="{status:true,message:\"成功增加一个题目\"}";
		}
		else {
			jsondata="{status:false,message:\"增加一个题目失败，请重新操作\"}";
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
