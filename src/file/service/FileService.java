package file.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import file.dao.FileDAO;
import file.domain.FileInfo;

public class FileService {
	@Autowired
	FileDAO fileDAO;
	
	public FileInfo saveFile(MultipartFile multipartFile, String realPath) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setConvertName(getRandomeName());
		fileInfo.setRealName(multipartFile.getOriginalFilename());
		
		File filePath = new File(realPath + fileInfo.getFilePath());
		System.out.println("path path!!!" + filePath.getAbsolutePath());
		
		try {
			filePath.createNewFile();
			multipartFile.transferTo(filePath);
			fileDAO.saveFile(fileInfo);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fileInfo;
	}
	
	public void getRealName(FileInfo fileInfo) {
		fileDAO.getRealName(fileInfo);
	}
	
	
	private String getRandomeName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
