package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SearchAllUserDao;

@Controller
public class SearchAllUser {
	//下面这个注解必须要
	@Autowired
	SearchAllUserDao searchAllUserDao;
	
	@RequestMapping(value="searchalluser")
	public void SearchAllUserDao(String pagenow,Model model,HttpServletResponse response){
		int pagenow1=Integer.parseInt(pagenow);
		int pagesize=3;
		int count=0;
		count=searchAllUserDao.searchcount();
		ArrayList<User> list=new ArrayList<User>();
		list=searchAllUserDao.searchfenyeuserdao(pagenow1,pagesize);
		
		model.addAttribute("list", list);
		String jsondata="";
		jsondata="{msg:\"哈哈\"}";
		jsondata="{count:\""+count+"\"}";
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println("接送噶挨个发"+jsonArray);
	//	jsonArray.add(jsondata);
		try {
			response.getWriter().print(jsonArray.toString());
			
			response.getWriter().close();
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

}
