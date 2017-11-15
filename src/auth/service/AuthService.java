package auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import user.dao.UserDao;
import user.domain.User;
import user.domain.UserForm;
import user.domain.UserLogin;

public class AuthService {
	@Autowired
	UserDao userDao;
	
	@Transactional
	public void join(UserForm userForm) {
		userDao.join(userForm);
	}

	public void update(UserForm userForm) {
		userDao.update(userForm);
	}
	
	public boolean login(UserLogin user) {
		return userDao.login(user);
	}
	
	public boolean idCheck(String id) {
		return userDao.idOvelapCheck(id);
	}
	
	public boolean subNameCheck(String subName) {
		return userDao.subNameOvelapCheck(subName);
	}
	
	public User getUser(String id) {
		return userDao.getUser(id);
	}
	
}
