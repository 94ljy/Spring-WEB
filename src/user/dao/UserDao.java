package user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import user.domain.User;

public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(User user) {
		jdbcTemplate.update("insert into user(id, password, name, email, subName, phoneNumber) values(?,?,?,?,?,?)"
				, user.getId(), user.getPassword(), user.getName(), user.getEmail(), user.getSubName(), user.getPhoneNumber());
	}
	
	public User get(String id) {
		User user = jdbcTemplate.queryForObject("select * from user where id = ?", new Object[] {id}, 
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet row, int arg1) throws SQLException {
						User user = new User();
						user.setId(row.getString("id"));
						user.setPassword(row.getString("password"));
						user.setName(row.getString("name"));
						user.setEmail(row.getString("email"));
						user.setSubName(row.getString("subName"));
						user.setPhoneNumber(row.getString("phoneNumber"));
						return user;
					}
		});
		return user;
	}
	
	public long userCount() {
		return jdbcTemplate.queryForObject("select count(*) from user", Long.class);
	}
	
	public void update(User user) {
		jdbcTemplate.update("update user set name = ?, email = ?, subName = ?, phoneNumber = ? where id = ?", 
				user.getName(), user.getEmail(), user.getSubName(), user.getPhoneNumber(), user.getId());
	}
	
	
}
