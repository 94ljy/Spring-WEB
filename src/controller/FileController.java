package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import file.domain.FileInfo;
import file.service.FileService;

@Controller
@RequestMapping(value="/file")
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		FileInfo fileInfo = fileService.saveFile(multipartFile, request.getServletContext().getRealPath(""));
		Map<String, String> result = new HashMap<>();
		result.put("result", "OK");
		result.put("url", "/file/" + fileInfo.getConvertName());
		return result;
	}
	
	@RequestMapping(value="/{fileName}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		String imageFolerPath = request.getServletContext().getRealPath("") + FileInfo.basePath;
		
		File file = new File(imageFolerPath + fileName);
		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setConvertName(fileName);
		fileService.getRealName(fileInfo);	
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileInfo.getRealName())
				.contentType(MediaType.IMAGE_JPEG)
				.contentLength(file.length())
				.body(resource);
	}
	
}



