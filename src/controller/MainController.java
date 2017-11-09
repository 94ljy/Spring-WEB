package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpSession session) {
		String url;
		
		if(session.getAttribute("user") == null) {
			url = "redirect:/auth/login";
		}else {
			url = "redirect:/board";
		}
		
		return url;
	}
		
}
