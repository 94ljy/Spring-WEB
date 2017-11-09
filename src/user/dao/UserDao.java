package user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import user.domain.User;
import user.domain.UserInfo;

public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void join(User user) {
		jdbcTemplate.update("insert into user(id, password) values(?,?)"
				, user.getId(), user.getPassword());
		jdbcTemplate.update("insert into userinfo(id, name, email, subName, phoneNumber) values(?,?,?,?,?)"
				, user.getId(), user.getUserInfo().getName(), 
				user.getUserInfo().getEmail(), user.getUserInfo().getSubName(), user.getUserInfo().getPhoneNumber());
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
							user.setUserInfo(new UserInfo(user));
							
							user.setId(row.getString("id"));
							user.getUserInfo().setName(row.getString("name"));
							user.getUserInfo().setEmail(row.getString("email"));
							user.getUserInfo().setSubName(row.getString("subName"));
							user.getUserInfo().setPhoneNumber(row.getString("phoneNumber"));
							
							return user;
						}
			});
		}catch(EmptyResultDataAccessException e) {}
		
		return user;
	}
	
	public User getUser(User user) {
		return this.getUser(user.getId());
	}
	
	public long userCount() {
		return jdbcTemplate.queryForObject("select count(*) from user", Long.class);
	}
	
	public void update(User user) {
		jdbcTemplate.update("update user set password = ? where id = ?", 
				user.getPassword(), user.getId());
		
		jdbcTemplate.update("update userinfo set name = ?, email = ?, subName = ?, phoneNumber = ?  where id = ?", 
				user.getUserInfo().getName(), user.getUserInfo().getEmail(), 
				user.getUserInfo().getSubName(), user.getUserInfo().getPhoneNumber(), user.getId());
	}
	
	public boolean login(User user) {
		long row = jdbcTemplate.queryForObject("select count(*) from user where id = ? and password = ?", 
				new Object[] {user.getId(), user.getPassword()} ,Long.class);

		return row == 1L ? true : false;
	}
	
}
