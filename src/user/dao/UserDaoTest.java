package user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import context.AppContext;
import user.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	@Autowired
	PlatformTransactionManager transactionManager;
	User user1;
	User user2;
	User user3;
	TransactionStatus txStatus;
	
	@Before
	public void init() {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		 txStatus = transactionManager.getTransaction(txDefinition);
		
		user1 = new User("user1", "1234", "유저1", "1234@gg.com", "subName1", "010-1111-1111");
		user2 = new User("user2", "2222", "유저2", "2222@gg.com", "subName2", "010-2222-2222");
		user3 = new User("user3", "3333", "유저2", "3333@gg.com", "subName3", "010-3333-3333");
	}
	@After
	public void shutDown() {
		transactionManager.rollback(txStatus);
	}
	
	@Test
	public void UserAddAndGet() {
		userDao.add(user1);
		User getUser1 = userDao.get(user1.getId());
		isSameUser(user1, getUser1);
		
		userDao.add(user2);
		User getUser2 = userDao.get(user2.getId());
		isSameUser(user2, getUser2);
		
		userDao.add(user3);
		User getUser3 = userDao.get(user3.getId());
		isSameUser(user3, getUser3);
	}
	
	@Test
	public void userCount() {
		userDao.add(user1);
		assertThat(userDao.userCount(), is(1L));
		
		userDao.add(user2);
		assertThat(userDao.userCount(), is(2L));
		
		userDao.add(user3);
		assertThat(userDao.userCount(), is(3L));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void doublePrimaryKey() {
		userDao.add(user1);
		userDao.add(user1);
		fail();
	}
	
	
	private void isSameUser(User user, User getUser) {
		assertThat(user.getId(), is(getUser.getId()));
		assertThat(user.getPassword(), is(getUser.getPassword()));
		assertThat(user.getName(), is(getUser.getName()));
		assertThat(user.getEmail(), is(getUser.getEmail()));
		assertThat(user.getSubName(), is(getUser.getSubName()));
		assertThat(user.getPhoneNumber(), is(getUser.getPhoneNumber()));
	}
	
}
