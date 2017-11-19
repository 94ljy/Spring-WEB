package file.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import file.domain.FileInfo;

public class FileDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void saveFile(FileInfo fileInfo) {
		jdbcTemplate.update("insert into file(real_name, convert_name) values(?, ?)"
				, fileInfo.getRealName(), fileInfo.getConvertName());
		long fileNo = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);
		fileInfo.setFileNo((int)fileNo);
	}
	
	public void getRealName(FileInfo fileInfo) {
		String realName = jdbcTemplate.queryForObject("select real_name from file where convert_name = ?"
				, new Object[] {fileInfo.getConvertName()}, String.class);
		fileInfo.setRealName(realName);
	}
	
}
