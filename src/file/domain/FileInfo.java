package file.domain;

public class FileInfo {
	
	public static final String basePath = "/WEB-INF/upload/resource/";
	
	int fileNo;
	String realName;
	String convertName;
	
	public int getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getConvertName() {
		return convertName;
	}
	
	public void setConvertName(String convertName) {
		this.convertName = convertName;
	}
	
	public String getFilePath() {
		return basePath + convertName;
	}
	
}
