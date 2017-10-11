package action;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.RegistDao;

@Controller
public class RegistAction {
	@Autowired
	RegistDao rg;
	
	@RequestMapping(value="regist")
	public String regist(User user){
		user.setAccount(user.getName());
		rg.registDao(user);
		return "login";
	}

		
}
