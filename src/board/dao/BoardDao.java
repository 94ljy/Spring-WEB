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
import user.domain.User;
import user.domain.UserInfo;

public class BoardDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<Board> boardMapper = new RowMapper<Board>() {
		
		@Override
		public Board mapRow(ResultSet row, int arg1) throws SQLException {
			Board board = new Board();
			board.setBoardNo(row.getString("board_no"));
			board.setBoardTitle(row.getString("board_title"));
			board.setBoardContent(row.getString("board_content"));
			board.setBoardTime(row.getDate("board_time"));
			board.setBoardCount(row.getInt("board_count"));
			board.setUser(new User(row.getString("subName")));
			
			return board;
		}
	};
	
	RowMapper<Board> boardListMapper = new RowMapper<Board>() {
		
		@Override
		public Board mapRow(ResultSet row, int arg1) throws SQLException {
			Board board = new Board();
	
			board.setBoardNo(row.getString("board_no"));
			board.setBoardTitle(row.getString("board_title"));
			board.setBoardTime(row.getDate("board_time"));
			board.setUser(new User(row.getString("subName")));
			
			return board;
		}
	};
	
	
	public void addBoard(Board board ) {
		jdbcTemplate.update("insert into board(board_title, board_content, board_time, board_count, user_id) "
				+ "values(?, ?, now(), 0, ?)", new Object[] { board.getBoardTitle(), board.getBoardContent(), board.getUser().getId()});
	}
	
	public Board getBoard(String boardNo) {
		Board board = jdbcTemplate
				.queryForObject("select b.board_no, b.board_title, b.board_content, b.board_time, b.board_count, u.subName "
						+ "from board as b, userinfo as u "
						+ "where b.user_id = u.id and b.board_no = ? "
						, new Object[] {boardNo}
						, boardMapper);
		return board;
	}
	
	public long getTotalPage() {
		return jdbcTemplate.queryForObject("select count(*) from board", Long.class);
	}
	
	public void updateBoardCount(String boardNo) {
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
	
}
