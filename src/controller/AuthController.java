package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import auth.service.AuthService;
import user.domain.IsAble;
import user.domain.UserForm;
import user.domain.UserFormValidator;
import user.domain.UserLogin;

@Controller
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(Model model) {
		return "/WEB-INF/joinForm.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Validated UserForm userForm, BindingResult result, ModelMap model) {
		String url;

		if(result.hasErrors()) {
			model.addAttribute("userForm", userForm);
			model.addAttribute("error", result.getAllErrors());
			url = "/WEB-INF/joinForm.jsp";
		}else {
			try {
				authService.join(userForm);
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
	public String login(UserLogin userLogin, HttpSession session) {
		String url;
		
		if( authService.login(userLogin) ) {
			session.setAttribute("user", authService.getUser(userLogin.getId()));
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
	
	@InitBinder("userForm")
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new UserFormValidator());
	}
	
}


