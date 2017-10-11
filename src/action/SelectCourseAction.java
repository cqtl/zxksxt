package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import model.StudentCourse;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SearchScoreDao;
import dao.SelectCourseDao;

@Scope(value="prototype")
@Controller("SelectCourseAction")
@RequestMapping(value="selectcourse")
public class SelectCourseAction {
	@Autowired
	SelectCourseDao selectCourseDao;
	@Autowired
	SearchScoreDao searchScoreDao;
	
	/**
	 * 跳转到学生选课页面
	 * @return
	 */
	@RequestMapping
	public String selectcoursepage(){
		return "selectcourse";
	}
	
	/**
	 * 查询所有课程
	 * @param response
	 */
	@RequestMapping(value="searchcourse")
	public void searchcourse(HttpServletResponse response){
		ArrayList<String> courselist = new ArrayList<String>();
		courselist=selectCourseDao.searchcourse();
		JSONArray jsonArray = JSONArray.fromObject(courselist);
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
	 * 提交学生的选课
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="tijiaocourse")
	public void tijiaocourse(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		String jsondata="";
		boolean issuccess=false;
		int userid=(Integer) session.getAttribute("successuserid");
		ArrayList<StudentCourse> sclist =new ArrayList<StudentCourse>();
		ArrayList<Student> list = searchScoreDao.searchstudentinfo(userid);
		int number=list.get(0).getNumber();
		String course[]=request.getParameterValues("course");
		for (String c:course) {
			StudentCourse studentCourse=new StudentCourse();
			studentCourse.setCourse(c);
			studentCourse.setIspass(0);
			studentCourse.setNumber(number);
			studentCourse.setScore((float) 0);
			sclist.add(studentCourse);
		}
		issuccess=selectCourseDao.tijiaocourse(sclist);
		if (issuccess) {
			jsondata="{\"status\":true,\"message\":\"提交成功\"}";
		}
		else {
			jsondata="{\"status\":false,\"message\":\"提交失败\"}";
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsondata);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
