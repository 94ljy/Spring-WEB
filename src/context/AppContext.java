package context;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.Driver;

import auth.service.AuthService;
import board.dao.BoardDao;
import board.service.BoardService;
import file.dao.FileDAO;
import file.service.FileService;
import user.dao.UserDao;

@Configuration
@EnableTransactionManagement
@PropertySource("/db.properties")
public class AppContext {
	
	@Value("${db.driverClass}")
	Class<? extends Driver> driverClass;
	@Value("${db.url}")
	String url;
	@Value("${db.username}")
	String username;
	@Value("${db.password}")
	String password;

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public AuthService userService() {
		return new AuthService();
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDao();
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardService();
	}
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao();
	}
	
	@Bean
	public FileService fileService() {
		return new FileService();
	}
	
	@Bean
	public FileDAO fileDAO() {
		return new FileDAO();
	}
	
}
