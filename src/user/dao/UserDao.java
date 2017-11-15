package user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import user.domain.User;
import user.domain.UserForm;
import user.domain.UserLogin;

public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void join(UserForm user) {
		jdbcTemplate.update("insert into user(id, password) values(?,?)"
				, user.getId(), user.getPassword());
		jdbcTemplate.update("insert into userinfo(id, name, email, subName, phoneNumber) values(?,?,?,?,?)"
				, user.getId(), user.getName(), 
				user.getEmail(), user.getSubName(), user.getPhoneNumber());
	}
	
	public User getUser(String id) {
		User user = null;
		try {
			 user = jdbcTemplate.queryForObject("select u.id, info.name, info.email, "
					+ "info.subName, info.phoneNumber from user as u, userinfo as info where u.id = info.id and u.id = ?", new Object[] {id}, 
					new RowMapper<User>() {
						@Override
						public User mapRow(ResultSet row, int arg1) throws SQLException {
							User user = new User();
							
							user.setId(row.getString("id"));
							user.setName(row.getString("name"));
							user.setEmail(row.getString("email"));
							user.setSubName(row.getString("subName"));
							user.setPhoneNumber(row.getString("phoneNumber"));
							
							return user;
						}
			});
		}catch(EmptyResultDataAccessException e) {}
		
		return user;
	}
	
	public User getUser(User user) {
		return this.getUser(user.getId());
	}
	
	public long getUserCount() {
		return jdbcTemplate.queryForObject("select count(*) from user", Long.class);
	}
	
	public void update(UserForm user) {
		jdbcTemplate.update("update user set password = ? where id = ?", 
				user.getPassword(), user.getId());
		
		jdbcTemplate.update("update userinfo set name = ?, email = ?, subName = ?, phoneNumber = ?  where id = ?", 
				user.getName(), user.getEmail(), 
				user.getSubName(), user.getPhoneNumber(), user.getId());
	}
	
	public boolean login(UserLogin user) {
		long row = jdbcTemplate.queryForObject("select count(*) from user where id = ? and password = ?", 
				new Object[] {user.getId(), user.getPassword()}, Long.class);

		return row == 1L ? true : false;
	}
	
	public boolean idOvelapCheck(String id) {
		long result = jdbcTemplate.queryForObject("select count(*) from user where id = ?", new Object[] {id}, Long.class);
		
		return result == 1L ? false : true;
	}
	
	public boolean subNameOvelapCheck(String subName) {
		long result = jdbcTemplate.queryForObject("select count(*) from userinfo where subName = ?", new Object[] {subName}, Long.class);
		
		if(result == 1L) return false;
		else return true;
		
	}
	
}
