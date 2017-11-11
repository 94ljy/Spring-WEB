package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CharacterEncodingFilter;

import user.domain.User;

@Controller
public class MainController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpSession session) {
		String url;
		
		if(session.getAttribute("user") == null) {
			url = "redirect:/auth/login";
		}else {
			System.out.println(session.getId());
			System.out.println(((User)session.getAttribute("user")).getId());
			url = "redirect:/board";
		}
		
		return url;
	}
		
}
