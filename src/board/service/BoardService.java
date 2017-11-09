package board.service;

import org.springframework.beans.factory.annotation.Autowired;

import board.dao.BoardDao;
import board.domain.Board;
import board.domain.BoardTable;
import user.dao.UserDao;

public class BoardService {
	@Autowired
	BoardDao boardDao;
	@Autowired
	UserDao userDao;
	
	public void addBoard(Board board) {
		boardDao.addBoard(board);
	}
	
	public Board getBoard(String boardNo ) {
		
		boardDao.updateBoardCount(boardNo);
		Board board = boardDao.getBoard(boardNo);
		
		return board;
	}
	
	public BoardTable getBoardTable(int nowPage) {
		BoardTable boardTable = new BoardTable();
		boardTable.setBoardList(boardDao.getBoardList(nowPage));

		int totalPage = (int)boardDao.getTotalPage();
		int firstPage = nowPage - (nowPage % 10 - 1);
		int lastPage = totalPage / 10;		
		if(totalPage % 10 > 0) { lastPage++; } 
		
		boardTable.setNowPage(nowPage);
		boardTable.setFirstPage(firstPage);
		if(lastPage < firstPage + 9) {
			boardTable.setLastPage(lastPage);
		}else {
			boardTable.setLastPage(firstPage + 9); 
		}
		
		return boardTable;
	}
	
}
