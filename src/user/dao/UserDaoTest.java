package user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
import user.domain.UserForm;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	@Autowired
	PlatformTransactionManager transactionManager;
	UserForm user1;
	UserForm user2;
	UserForm user3;
	TransactionStatus txStatus;
	
	@Before
	public void init() {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		 txStatus = transactionManager.getTransaction(txDefinition);
		
		user1 = new UserForm("user1", "1234", "유저1", "1234@gg.com", "subName1", "010-1111-1111");
		user2 = new UserForm("user2", "2222", "유저2", "2222@gg.com", "subName2", "010-2222-2222");
		user3 = new UserForm("user3", "3333", "유저2", "3333@gg.com", "subName3", "010-3333-3333");
	}
	
	@After
	public void destroy() {
		transactionManager.rollback(txStatus);
	}
	
	@Test
	public void UserAddAndGet() {
		userDao.join(user1);
		User getUser1 = userDao.getUser(user1.getId());
		isSameUser(user1, getUser1);
		
		userDao.join(user2);
		User getUser2 = userDao.getUser(user2.getId());
		isSameUser(user2, getUser2);
		
		userDao.join(user3);
		User getUser3 = userDao.getUser(user3.getId());
		isSameUser(user3, getUser3);
	}
	
	@Test
	public void userCount() {
		long count = userDao.getUserCount();
		
		userDao.join(user1);
		assertThat(userDao.getUserCount(), is(++count));
		
		userDao.join(user2);
		assertThat(userDao.getUserCount(), is(++count));
		
		userDao.join(user3);
		assertThat(userDao.getUserCount(), is(++count));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void doublePrimaryKey() {
		userDao.join(user1);
		userDao.join(user1);
		fail();
	}
	
	@Test
	public void update() {
		userDao.join(user1);
		User getUser1 = userDao.getUser(user1.getId());
		isSameUser(user1, getUser1);
		
		user1.setName("수정");
		assertThat(user1.getName(), is(not(getUser1.getName())));
		
		userDao.update(user1);
		getUser1 = userDao.getUser(user1.getId());
		
		assertThat(user1.getName(), is(getUser1.getName()));
		
	}
	
		
	private void isSameUser(User user, User getUser) {
		assertThat(user.getId(), is(getUser.getId()));
		assertThat(user.getName(), is(getUser.getName()));
		assertThat(user.getEmail(), is(getUser.getEmail()));
		assertThat(user.getSubName(), is(getUser.getSubName()));
		assertThat(user.getPhoneNumber(), is(getUser.getPhoneNumber()));
	}
	
}
