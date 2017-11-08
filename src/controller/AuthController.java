package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import user.dao.UserDao;
import user.domain.User;
import user.service.UserService;

@Controller
@RequestMapping(value="/auth")
@SessionAttributes(value="user")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(Model model) {
		model.addAttribute("user", new User());
		System.out.println("ssss");
		return "/WEB-INF/joinForm.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute User user, BindingResult result, SessionStatus sessionStatus) {
		String url;
		if(!result.hasErrors()) {
			url = "/WEB-INF/joinForm.jsp";
		}else {
			userService.add(user);
			sessionStatus.setComplete();
			url = "/WEB-INF/welcome.jsp";
		}
		
		return url;
	}
	
	/*@ModelAttribute("user")
	public User user() {
		return new User();
	}*/
	
}
