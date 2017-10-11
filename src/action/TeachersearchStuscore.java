package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SearchScoreDao;

@Scope(value="prototype")
@Controller("TeachersearchStuscore")
@RequestMapping(value="teachersearchstuscore")
public class TeachersearchStuscore {
	@Autowired
	SearchScoreDao searchScoreDao;
	
	/**
	 * 跳转到学生成绩页面
	 * @return
	 */
	@RequestMapping
	public String stusituationpage(){
		return "stusituation";
	}
	/**
	 * 教师查询学生成绩
	 * @param kemu
	 * @param banji
	 * @param response
	 * @param session
	 */
	@RequestMapping(value="searchstuscore")
	public void searchuscore(String kemu,String banji,HttpServletResponse response,HttpSession session){
		ArrayList<Object[]> scoreList = new ArrayList<Object[]>();
		scoreList=searchScoreDao.teachersearchscore(kemu, banji);
		JSONArray jsonArray = JSONArray.fromObject(scoreList);
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

}
