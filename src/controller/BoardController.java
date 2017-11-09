package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.domain.BoardTable;
import board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String board(@RequestParam(defaultValue="1") int nowPage, ModelMap model) {
		BoardTable boardTable = boardService.getBoardTable(nowPage);
		model.addAttribute("boardTable", boardTable);
		return "/WEB-INF/board.jsp";
	}
	
}
