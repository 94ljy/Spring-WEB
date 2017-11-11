package controller;

import java.nio.file.attribute.UserPrincipalLookupService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import auth.service.AuthService;
import user.domain.IsAble;
import user.domain.User;
import user.domain.UserInfo;

@Controller
@RequestMapping(value="/auth")
@SessionAttributes(value="userJoinForm")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(Model model) {
		model.addAttribute("userJoinForm", new User());
		return "/WEB-INF/joinForm.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute User userJoinForm, @ModelAttribute UserInfo userInfo, BindingResult result, SessionStatus sessionStatus) {
		String url;
		userJoinForm.setUserInfo(userInfo);
		
		if(result.hasErrors()) {
			url = "/WEB-INF/joinForm.jsp";
		}else {
			try {
				authService.join(userJoinForm);
				sessionStatus.setComplete();
				url = "/WEB-INF/welcome.jsp";
			}catch(Exception e){
				url = "/WEB-INF/joinForm.jsp";
			}
			
		}
		
		return url;
	}
	
	@RequestMapping(value="/join/idCheck/{id}")
	@ResponseBody
	public IsAble idCheck(@PathVariable String id) {
		IsAble result = new IsAble();
		if(authService.idCheck(id)) {
			result.setAble(true);
		}else {
			result.setAble(false);
		}
		return result;
	}
	
	@RequestMapping(value="/join/subNameCheck/{subName}")
	@ResponseBody
	public IsAble subNameCheck(@PathVariable String subName) {
		IsAble result = new IsAble();
		if(authService.subNameCheck(subName)) {
			result.setAble(true);
		}else {
			result.setAble(false);
		}
		return result;
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		return "/WEB-INF/loginForm.jsp";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, HttpSession session) {
		String url;
		
		if( authService.login(user) ) {
			session.setAttribute("user", authService.getUser(user.getId()));
			url = "redirect:/board";
		}else {
			url = "/WEB-INF/loginForm.jsp";
		}
		
		return url;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String login(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		return "redirect:/";
	}
	

	
}


