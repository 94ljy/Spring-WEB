package board.service;



import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import board.domain.Board;
import board.domain.BoardTable;
import context.AppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	
	
	@Test
	public void countBoard() {
		Board board1 = boardService.getBoard("3");
		Board board2 = boardService.getBoard("3");

		assertThat(board1.getBoardCount(), is(not(board2.getBoardCount())));
	}
	
	@Test
	public void getBoardTable() {
		int nowPage = 2;
		BoardTable boardTable = boardService.getBoardTable(nowPage);
		
		assertThat(boardTable.getFirstPage(), is(1));
		assertThat(boardTable.getLastPage(), is(3));
		assertThat(boardTable.getNowPage(), is(nowPage));
		
		assertThat(boardTable.getBoardList().size(), is(10));
	}
	
}










