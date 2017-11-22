package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {
	
	@RequestMapping(value="/chat", method=RequestMethod.GET)
	public String chat(HttpSession session) {
		
		if(session.getAttribute("user") != null)
			return "chat";
		else 
			return "redirect:/"; 
	}
	
}
