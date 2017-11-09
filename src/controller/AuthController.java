package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import auth.service.AuthService;
import user.domain.User;
import user.domain.UserInfo;

@Controller
@RequestMapping(value="/auth")
@SessionAttributes(value="user")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(Model model) {
		model.addAttribute("user", new User());
		return "/WEB-INF/joinForm.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute User user, @ModelAttribute UserInfo userInfo, BindingResult result, SessionStatus sessionStatus) {
		String url;
		user.setUserInfo(userInfo);
		
		if(result.hasErrors()) {
			url = "/WEB-INF/joinForm.jsp";
		}else {
			authService.join(user);
			sessionStatus.setComplete();
			url = "/WEB-INF/welcome.jsp";
		}
		
		return url;
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		return "/WEB-INF/loginForm.jsp";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, HttpSession session) {
		String url;
		
		if( authService.login(user) ) {
			session.setAttribute("user", user);
			System.out.println("11");
			url = "redirect:/";
		}else {
			System.out.println("22");
			url = "/WEB-INF/loginForm.jsp";
		}
		
		return url;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String login(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}


