package board.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import board.domain.Board;
import context.AppContext;
import user.dao.UserDao;
import user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class BoardDaoTest {
	
	@Autowired
	BoardDao boardDao;
	@Autowired
	UserDao userDao;
	@Autowired
	PlatformTransactionManager transactionManager;
	TransactionStatus txStatus;

	User user;
	Board board1, board2;
	
	
	@Before
	public void init() {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		txStatus = transactionManager.getTransaction(txDefinition);
		user = new User("test", "test", "test", "test", "test", "test");
		board1 = new Board("力格1", "郴侩1", user);
		board2 = new Board("力格2", "郴侩2", user);
	}
	
	@After
	public void destroy() {
		transactionManager.rollback(txStatus);
	}
	
	@Test
	public void addBoard() {
		
		long totalPage = boardDao.getTotalPage();
		boardDao.addBoard(board1);
		assertThat(boardDao.getTotalPage(), is(not(totalPage)));
		
	}
	
	//@Test
	public void getBoard() {
		Board getBoard1 = boardDao.getBoard(3);
		assertThat(getBoard1.getBoardTitle(), is(board1.getBoardTitle()));
	}
	
	//@Test
	public void getBoardList() {
		List<Board> boardList = boardDao.getBoardList(1);
		assertThat(boardList.size(), is(3));
		
		Board getBoard1 = boardList.get(0);
		assertThat(getBoard1.getBoardNo(), is("5"));
		
	}
	
}

