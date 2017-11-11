package board.service;

import org.springframework.beans.factory.annotation.Autowired;

import auth.service.AuthService;
import board.dao.BoardDao;
import board.domain.Board;
import board.domain.BoardTable;
import board.domain.Reply;
import board.domain.ReplyTable;
import user.dao.UserDao;

public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public void addBoard(Board board) {
		boardDao.addBoard(board);
	}
	
	public Board getBoard(int boardNo ) {
		
		boardDao.updateBoardCount(boardNo);
		Board board = boardDao.getBoard(boardNo);
		board.setReplyTable(this.getReply(boardNo, 1));
		
		return board;
	}
	
	public BoardTable getBoardTable(int page) {
		BoardTable boardTable = new BoardTable();
		boardTable.setBoardList(boardDao.getBoardList(page));

		int totalPage = (int)boardDao.getTotalPage();
		int firstPage = ((page-1)/10) + 1;
		int lastPage = totalPage / 10;		
		if(totalPage % 10 > 0) { lastPage++; } 
		
		boardTable.setNowPage(page);
		boardTable.setFirstPage(firstPage);
		if(lastPage < firstPage + 9) {
			boardTable.setLastPage(lastPage);
		}else {
			boardTable.setLastPage(firstPage + 9); 
		}
		
		return boardTable;
	}
	
	
	public ReplyTable getReply(int boardNo, int page){
		ReplyTable replyTable = new ReplyTable();
		replyTable.setReplys(boardDao.getReply(boardNo, page));
		
		int totalReply = boardDao.getReplyCount(boardNo);
		int firstPage = ((page-1)/10) + 1;
		int lastPage = totalReply / 10;
		if(totalReply % 10 > 0) { lastPage++; } 
		
		replyTable.setFirstPage(firstPage);
		replyTable.setNowPage(page);
		
		if(lastPage < firstPage + 9 )
			replyTable.setLastPage(lastPage);
		else
			replyTable.setFirstPage(firstPage + 9);
		
		return replyTable;
		
	}
	
	public void writeReply(Reply reply) {
		boardDao.writeReply(reply);
	}
	
	
}
