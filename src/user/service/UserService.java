package user.service;

import org.springframework.beans.factory.annotation.Autowired;

import user.dao.UserDao;
import user.domain.User;

public class UserService {
	@Autowired
	UserDao userDao;
	
	public void add(User user) {
		userDao.add(user);
	}
	
	public User get(String id) {
		return userDao.get(id);
	}
	
	public void update(User user) {
		userDao.update(user);
	}
	
}
