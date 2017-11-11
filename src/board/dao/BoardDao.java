package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.domain.Board;
import board.domain.Reply;
import user.domain.User;
import user.domain.UserInfo;

public class BoardDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<Board> boardMapper = new RowMapper<Board>() {
		
		@Override
		public Board mapRow(ResultSet row, int arg1) throws SQLException {
			Board board = new Board();
			board.setBoardNo(row.getInt("board_no"));
			board.setBoardTitle(row.getString("board_title"));
			board.setBoardContent(row.getString("board_content"));
			board.setBoardTime(row.getDate("board_time"));
			board.setBoardCount(row.getInt("board_count"));
			board.setUser(new User(row.getString("subName")));
			board.getUser().setId(row.getString("id"));
			
			return board;
		}
	};
	
	RowMapper<Reply> replyMapper = new RowMapper<Reply>() {
		
		@Override
		public Reply mapRow(ResultSet row, int arg1) throws SQLException {
			Reply reply = new Reply();
			
			reply.setReplyNo(row.getInt("reply_no"));
			reply.setReplyContent(row.getString("reply_content"));
			reply.setReplyDate(row.getDate("reply_date"));
			reply.setUser(new User(row.getString("subName")));
			reply.getUser().setId(row.getString("id"));
			
			return reply;
		}
	};
	
	RowMapper<Board> boardListMapper = new RowMapper<Board>() {
		
		@Override
		public Board mapRow(ResultSet row, int arg1) throws SQLException {
			Board board = new Board();
	
			board.setBoardNo(row.getInt("board_no"));
			board.setBoardTitle(row.getString("board_title"));
			board.setBoardTime(row.getDate("board_time"));
			board.setBoardCount(row.getInt("board_count"));
			board.setUser(new User(row.getString("subName")));
			
			return board;
		}
	};
	
	
	public void addBoard(Board board ) {
		jdbcTemplate.update("insert into board(board_title, board_content, board_time, board_count, user_id) "
				+ "values(?, ?, now(), 0, ?)", new Object[] { board.getBoardTitle(), board.getBoardContent(), board.getUser().getId()});
	}
	
	public Board getBoard(int boardNo) {
		Board board = jdbcTemplate
				.queryForObject("select b.board_no, b.board_title, b.board_content, b.board_time, b.board_count, u.id, u.subName "
						+ "from board as b, userinfo as u "
						+ "where b.user_id = u.id and b.board_no = ? "
						, new Object[] {boardNo}
						, boardMapper);
		return board;
	}
	
	public long getTotalPage() {
		return jdbcTemplate.queryForObject("select count(*) from board", Long.class);
	}
	
	public void updateBoardCount(int boardNo) {
		jdbcTemplate.update("update board set board_count = board_count + 1 where board_no = ?"
				, new Object[] {boardNo});
	}
	
	public List<Board> getBoardList(int page) {
		int startNo = (page - 1) * 10;
		List<Board> boards = jdbcTemplate
				.query("select b.board_no, b.board_title, b.board_time, b.board_count, u.subName "
						+ "from board as b, userinfo as u "
						+ "where b.user_id = u.id "
						+ "order by b.board_no desc "
						+ "limit ?, 10 "
						, new Object[] {startNo}
						, boardListMapper);
		return boards;
	}
	

	public List<Reply> getReply(int boardNo, int page){
		List<Reply> replys = jdbcTemplate.query(
				"select r.reply_no, r.reply_content, r.reply_date, u.subName u.id"
			  + "from board b, reply r, userinfo u "
			  + "where b.board_no = r.board_no and u.id = r.user_id and r.board_no = ? "
			  + "order by r.reply_no desc "
			  + "limit ?, 5", new Object[] {boardNo, (page-1) * 5}, replyMapper);
		return replys;
	}
	
	public int getReplyCount(int boardNo) {
		long count = jdbcTemplate.queryForObject("select count(*) from reply where board_no = ?", new Object[] {boardNo}, Long.class);
		return (int)count;
	}
	
	public void writeReply(Reply reply) {
		jdbcTemplate.update("insert into reply(reply_content, reply_date, user_id, board_no) values(?, now(), ?, ?)"
				, reply.getReplyContent(), reply.getUser().getId(), reply.getBoard().getBoardNo());
	}
	
	
}
