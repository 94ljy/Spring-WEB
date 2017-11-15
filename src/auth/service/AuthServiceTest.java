package auth.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
import user.domain.UserForm;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class AuthServiceTest {
	@Autowired
	UserDao userDao;
	@Autowired
	PlatformTransactionManager transactionManager;
	TransactionStatus txStatus;
	
	UserForm user1;
	UserForm user2;
	UserForm user3;
	
	
	@Before
	public void init() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		txStatus = transactionManager.getTransaction(definition);
		
		user1 = new UserForm("user1", "1234", "����1", "1234@gg.com", "subName1", "010-1111-1111");
		user2 = new UserForm("user2", "2222", "����2", "2222@gg.com", "subName2", "010-2222-2222");
		user3 = new UserForm("user3", "3333", "����2", "3333@gg.com", "subName3", "010-3333-3333");
	}
	
	@After
	public void destroy() {
		transactionManager.rollback(txStatus);
	}
	
	
	//user���̺�, userinfo���̺� �� ���� �μ�Ʈ�� �Ѱ��� ���н� �ѹ� �׽�Ʈ
	@Test
	public void joinSubInfoError() {
		userDao.join(user1);
		User getUser1 = userDao.getUser(user1.getId());
		
		isSameUser(user1, getUser1);
		
		try {
			user2.setSubName(user1.getSubName());
			userDao.join(user2);
			fail();
		}catch(Exception e) {}
		
		assertThat(userDao.getUser(user2.getId()) , is(nullValue()));
	}
	
	
	private void isSameUser(User user, User getUser) {
		assertThat(user.getId(), is(getUser.getId()));
		assertThat(user.getName(), is(getUser.getName()));
		assertThat(user.getEmail(), is(getUser.getEmail()));
		assertThat(user.getSubName(), is(getUser.getSubName()));
		assertThat(user.getPhoneNumber(), is(getUser.getPhoneNumber()));
	}
	
}















