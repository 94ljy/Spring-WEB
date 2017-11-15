package controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import board.domain.Board;
import board.domain.BoardTable;
import board.domain.Reply;
import board.service.BoardService;
import user.domain.User;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	
	@RequestMapping(value= {"/{page}", ""}, method=RequestMethod.GET)
	public String board(@PathVariable Optional<Integer> page, ModelMap model) {
		int requestPage = 1;
		if(page.isPresent()) requestPage = page.get();
		
		BoardTable boardTable = boardService.getBoardTable(requestPage);
		model.addAttribute("boardTable", boardTable);
		return "/WEB-INF/boardList.jsp";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String writeBoard(HttpSession session) {
		String url;
		if(session.getAttribute("user") == null)
			url = "redirect:/board";
		else
			url = "/WEB-INF/boardWrite.jsp";
		
		return url;
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writeBoard(Board board, HttpSession session) {
		board.setUser((User)session.getAttribute("user"));
		System.out.println(board.getBoardTitle());
		boardService.addBoard(board);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/view/{boardNo}", method=RequestMethod.GET)
	public String boardView(@PathVariable int boardNo,@RequestParam(name="page", defaultValue="1") int page ,ModelMap model) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("replyTable", board.getReplyTable());
		model.addAttribute("page",page);
		
		return "/WEB-INF/boardView.jsp";
	}
	
	@RequestMapping(value="/del/{boardNo}")
	public String boardView(@PathVariable int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		
		boardService.boardDelete(board);
		
		return "redirect:/board";
	}
	
	
	@RequestMapping(value="reply/write", method=RequestMethod.POST)
	@ResponseBody
	public String writeReply(@ModelAttribute Reply reply, int boardNo , HttpSession session) {
		User user = (User)session.getAttribute("user");
		Board board = new Board();
		board.setBoardNo(boardNo);
		
		reply.setUser(user);
		reply.setBoard(board);
		
		boardService.writeReply(reply);
		
		return "{success : true}";
	}
	
	@RequestMapping(value="reply/{boardNo}/page/{page}")
	public String getReply(@PathVariable int boardNo, @PathVariable int page, ModelMap model) {
		
		model.addAttribute("replyTable", boardService.getReplyTable(boardNo, page));
		
		return "/WEB-INF/boardReply.jsp";
	}
	
	@RequestMapping(value="reply/modify")
	@ResponseBody
	public String replyModify(Reply reply){
		if(boardService.replyModify(reply)) {
			return "{success : true}";
		}else {
			return "{success : false}";
		}
	}
	
	@RequestMapping(value="reply/del/{replyNo}")
	@ResponseBody
	public String replyDelete(@PathVariable int replyNo) {
		Reply reply = new Reply();
		reply.setReplyNo(replyNo);
		if(boardService.replyDelete(reply)) {
			return "{success : true}";
		}else {
			return "{success : false}";
		}
	}
	
	
	@RequestMapping(value="modify/{boardNo}" ,method=RequestMethod.GET)
	public String boardModify(@PathVariable int boardNo, ModelMap model) {
		Board board = boardService.getBoard(boardNo);
		
		model.addAttribute("board", board);
		
		return "/WEB-INF/boardModify.jsp";
	}
	
	@RequestMapping(value="modify" ,method=RequestMethod.POST)
	public String boardModify(@ModelAttribute Board board) {
		boardService.updateBoard(board);
		
		return "redirect:/board/view/"+ board.getBoardNo();
	}
	
	

	
}
