package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Answers;
import model.Shijuan;
import model.Subjects;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ChutiDao;
import dao.KaoshiDao;

@Scope(value="prototype")
@Controller("kaoshi")
@RequestMapping(value="kaoshi")
public class KaoshiAction {
	@Autowired
	KaoshiDao kaoshidao;
	@Autowired
	ChutiDao chutiDao;

	
	/**
	 * 考生进入考试
	 * @param course
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String testsearchsubject(String course,HttpServletResponse response,Model model){
		course="操作系统";
		String examinationname="";
		int examinationtime;
		String startexaminationtime = "";
		ArrayList<Object[]> tiankongList=new ArrayList<Object[]>();
		String tiankongtimu = null;
		ArrayList<Object[]> danxuanList=new ArrayList<Object[]>();
		String danxuantimu = null;
		ArrayList<Object[]> duoxuanList=new ArrayList<Object[]>();
		String duoxuantimu = null;
		ArrayList<Object[]> jiandaList=new ArrayList<Object[]>();
		String jiandatimu = null;
		ArrayList<Answers> danxuanansList=new ArrayList<Answers>();
		ArrayList<Answers> duoxuanansList=new ArrayList<Answers>();
		ArrayList<Answers> tiankongansList=new ArrayList<Answers>();
		ArrayList<Object[]> list=new ArrayList<Object[]>();
		list=kaoshidao.SearchsubjectDao(course);
		String sub[];        //题目id
		sub=(((Shijuan) list.get(list.size()-1)[0]).getSubjectIds()).split(",");
		String scores[];
		scores=(((Shijuan) list.get(list.size()-1)[0]).getScore()).split(",");     //list.size()-1得到最近的一场考试
		examinationname=((Shijuan) list.get(list.size()-1)[0]).getName();
		examinationtime=(Integer) list.get(list.size()-1)[1];
		startexaminationtime=(String) list.get(list.size()-1)[2];
		
		for (int i = 0; i < sub.length; i++) {
			if (chutiDao.makesub(sub[i], "填空题").size()>0) {
				Object[] objects=new Object[3];
				int j=0;
				for (Object object:chutiDao.makesub(sub[i], "填空题").get(0)) {
					objects[j]=object;
					j++;
				}
				objects[2]=scores[i];
				tiankongList.add(objects);
				tiankongansList.add(chutiDao.makeans(sub[i]).get(0));
				tiankongtimu="填空题";
			}
			if (chutiDao.makesub(sub[i], "单选题").size()>0) {
				Object[] objects=new Object[3];
				int j=0;
				for (Object object:chutiDao.makesub(sub[i], "单选题").get(0)) {
					objects[j]=object;
					j++;
				}
				objects[2]=scores[i];
				danxuanList.add(objects);
				danxuanansList.add(chutiDao.makeans(sub[i]).get(0));
				danxuantimu="单选题";
			}
			if (chutiDao.makesub(sub[i], "多选题").size()>0) {
				Object[] objects=new Object[3];
				int j=0;
				for (Object object:chutiDao.makesub(sub[i], "多选题").get(0)) {
					objects[j]=object;
					j++;
				}
				objects[2]=scores[i];
				duoxuanList.add(objects);
				duoxuanansList.add(chutiDao.makeans(sub[i]).get(0));
				duoxuantimu="多选题";
			}
			if (chutiDao.makesub(sub[i], "简答题").size()>0) {
				Object[] objects=new Object[3];
				int j=0;
				for (Object object:chutiDao.makesub(sub[i], "简答题").get(0)) {
					objects[j]=object;
					j++;
				}
				objects[2]=scores[i];
				jiandaList.add(objects);
				jiandatimu="简答题";
			}
		}
	
	model.addAttribute("tiankong", tiankongList);
	model.addAttribute("tiankongans", tiankongansList);
	model.addAttribute("danxuan", danxuanList);
	model.addAttribute("duoxuan", duoxuanList);
	model.addAttribute("jianda", jiandaList);
	model.addAttribute("danxuanans", danxuanansList);
	model.addAttribute("duoxuanans", duoxuanansList);
	model.addAttribute("danxuantimu", danxuantimu);
	model.addAttribute("duoxuantimu", duoxuantimu);
	model.addAttribute("jiandatimu", jiandatimu);
	model.addAttribute("tiankongtimu", tiankongtimu);
	model.addAttribute("examinationname", examinationname);
	model.addAttribute("examinationtime", examinationtime);
	model.addAttribute("startexaminationtime", startexaminationtime);
	return "Kaoshi";
	}
	
	/**
	 * 考生提交答案
	 * @param subans
	 * @param response
	 */
	@RequestMapping(value="tijiao")
	public void tijiao(String subid,String shijuanname,HttpServletResponse response,HttpServletRequest request,HttpSession session){
		String jsondata="";
		int userid=(Integer) session.getAttribute("successuserid");
		String subids[];
		subids=subid.split(",");
		String answers[] = new String[subids.length];
		for (int i = 0; i < subids.length; i++) {
			answers[i]="";
			request.getParameterValues(subids[i]);
			for (int j = 0; j < request.getParameterValues(subids[i]).length; j++) {
				answers[i]=answers[i]+request.getParameterValues(subids[i])[j]+",";
			}
			answers[i]=answers[i].substring(0, answers[i].length()-1);
		}
		boolean issucess=kaoshidao.tijiao(subids, answers, userid,shijuanname);
		if (issucess) {
			jsondata="{\"status\":true,\"message\":\"提交成功\"}";
		}
		else {
			jsondata="{\"status\":true,\"message\":\"提交失败\"}";
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
