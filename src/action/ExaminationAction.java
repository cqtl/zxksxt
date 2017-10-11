package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import model.Examination;
import model.User;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ExaminationDao;

@Scope(value="prototype")
@Controller("ExaminationAction")
@RequestMapping(value="examination")
public class ExaminationAction {
	
	@Autowired
	ExaminationDao examdao;
	
	@RequestMapping(value="fabu")
	public String kaoshifabu(){
		return "examination";
	}
	
	@RequestMapping
	public void exam(String kemu,String name,HttpServletResponse response){
		ArrayList<User> list =new ArrayList<User>();
		list=examdao.searchname(kemu, name);
		
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
	 * 保存考试
	 * @param examination
	 * @param response
	 */
	@RequestMapping(value="save")
	public void save(Examination examination,HttpServletResponse response){
		String jsondata="";
		examination.setState(0);
		boolean issuccess=false;
		issuccess=examdao.saveexam(examination);
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"保存成功\"}";
		}
		else {
			jsondata="{\"sataus\":false,\"message\":\"保存失败\"}";
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
