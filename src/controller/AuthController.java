package controller;

import java.util.HashMap;
import java.util.Map;

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

import auth.service.AuthService;
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
		return "joinForm";
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
				url = "welcome";
			}catch(Exception e){
				url = "joinForm";
			}
			
		}
		
		return url;
	}
	
	@RequestMapping(value="/join/idCheck/{id}")
	@ResponseBody
	public Map<String, String> idCheck(@PathVariable String id) {
		Map<String, String> result = new HashMap<>();
		
		if(authService.idCheck(id)) {
			result.put("able", "true");
		}else {
			result.put("able", "false");
		}
		
		return result;
	}
	
	@RequestMapping(value="/join/subNameCheck/{subName}")
	@ResponseBody
	public Map<String, String> subNameCheck(@PathVariable String subName) {
		Map<String, String> result = new HashMap<>();
		if(authService.subNameCheck(subName)) {
			result.put("able", "true");
		}else {
			result.put("able", "false");
		}
		return result;
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserLogin userLogin, HttpSession session) {
		String url;
		
		if( authService.login(userLogin) ) {
			session.setAttribute("user", authService.getUser(userLogin.getId()));
			url = "redirect:/board";
		}else {
			url = "loginForm";
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


