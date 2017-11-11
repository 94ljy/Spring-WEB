package board.domain;

import java.util.Date;
import java.util.List;

import user.domain.User;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date boardTime;
	private int boardCount;
	private User user;
	
	private ReplyTable replyTable;


	public Board() {}
	
	public Board(String boardTitle, String boardContent, User user) {
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.user = user;
	}
	
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardTime() {
		return boardTime;
	}
	public void setBoardTime(Date boardTime) {
		this.boardTime = boardTime;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public ReplyTable getReplyTable() {
		return replyTable;
	}

	public void setReplyTable(ReplyTable replyTable) {
		this.replyTable = replyTable;
	}
	
}
