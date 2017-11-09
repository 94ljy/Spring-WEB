package auth.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import context.AppContext;
import user.dao.UserDao;
import user.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class AuthServiceTest {
	@Autowired
	UserDao userDao;
	@Autowired
	PlatformTransactionManager transactionManager;
	TransactionStatus txStatus;
	
	User user1;
	User user2;
	User user3;
	
	
	@Before
	public void init() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		txStatus = transactionManager.getTransaction(definition);
		
		user1 = new User("user1", "1234", "유저1", "1234@gg.com", "subName1", "010-1111-1111");
		user2 = new User("user2", "2222", "유저2", "2222@gg.com", "subName2", "010-2222-2222");
		user3 = new User("user3", "3333", "유저2", "3333@gg.com", "subName3", "010-3333-3333");
	}
	
	@After
	public void destroy() {
		transactionManager.rollback(txStatus);
	}
	
	
	//user테이블, userinfo테이블 두 개의 인설트중 한개라도 실패시 롤백 테스트
	@Test
	public void joinSubInfoError() {
		userDao.join(user1);
		User getUser1 = userDao.getUser(user1.getId());
		
		isSameUser(user1, getUser1);
		
		try {
			user2.setUserInfo(user1.getUserInfo());
			userDao.join(user2);
		}catch(Exception e) {}
		
		assertThat(userDao.getUser(user2.getId()) , is(nullValue()));
	}
	
	
	private void isSameUser(User user, User getUser) {
		assertThat(user.getId(), is(getUser.getId()));
		assertThat(user.getUserInfo().getName(), is(getUser.getUserInfo().getName()));
		assertThat(user.getUserInfo().getEmail(), is(getUser.getUserInfo().getEmail()));
		assertThat(user.getUserInfo().getSubName(), is(getUser.getUserInfo().getSubName()));
		assertThat(user.getUserInfo().getPhoneNumber(), is(getUser.getUserInfo().getPhoneNumber()));
	}
	
}















