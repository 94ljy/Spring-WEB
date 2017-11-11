package auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import user.dao.UserDao;
import user.domain.User;

public class AuthService {
	@Autowired
	UserDao userDao;
	
	@Transactional
	public void join(User userJoinForm) {
		userDao.join(userJoinForm);
	}

	public void update(User user) {
		userDao.update(user);
	}
	
	public boolean login(User user) {
		return userDao.login(user);
	}
	
	public boolean idCheck(String id) {
		return userDao.idCheck(id);
	}
	
	public boolean subNameCheck(String subName) {
		return userDao.subNameCheck(subName);
	}
	
	public User getUser(String id) {
		return userDao.getUser(id);
	}
	
}
