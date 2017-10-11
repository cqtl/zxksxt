package action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Examination;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ExaminationDao;

@Scope(value="prototype")
@Controller("searchexaminationinfo")
@RequestMapping(value="searchexamination")
public class SearchexaminationAction {
	@Autowired
	ExaminationDao examinationDao;
	
	/**
	 * 查询未考的考试
	 * @param response
	 */
	@RequestMapping(value="search")
	public void search(HttpServletResponse response){
		ArrayList<Examination> examlist = new ArrayList<Examination>();
		examlist=examinationDao.searchunexamination();
		JSONArray jsonArray=JSONArray.fromObject(examlist);
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
	 * 显示点击考试的具体信息
	 * @param model
	 * @param exam
	 * @return
	 */
	@RequestMapping(value="examinationdetail")
	public String examinationdetail(Model model,Integer exam){
		ArrayList<Examination> examlist = new ArrayList<Examination>();
		examlist=examinationDao.searchunexamination(exam);
		StringBuffer date = new StringBuffer();
		String dates[]=examlist.get(0).getExamtime().split("-");
		date.append(dates[0]+"年");
		if (Integer.parseInt(dates[1])<10) {
			date.append(dates[1].substring(1)+"月");
		}
		else {
			date.append(dates[1]+"月");
		}
		if (Integer.parseInt(dates[2].split(" ")[0])<10) {
			date.append(dates[2].split(" ")[0].substring(1)+"日");
		}
		else {
			date.append(dates[2].split(" ")[0]+"日");
		}
		if (Integer.parseInt(dates[2].split(" ")[1].split(":")[0])<10) {
			date.append(dates[2].split(" ")[1].split(":")[0].substring(1)+"点");
		}
		else {
			date.append(dates[2].split(" ")[1].split(":")[0]+"点");
		}
		if (Integer.parseInt(dates[2].split(" ")[1].split(":")[1])<10) {
			date.append(dates[2].split(" ")[1].split(":")[1].substring(1)+"分");
		}
		else {
			date.append(dates[2].split(" ")[1].split(":")[1]+"分");
		}
		
		examlist.get(0).setExamtime(date.toString());
		model.addAttribute("exam", examlist);
		return "Examinationdetail";
	}

}
