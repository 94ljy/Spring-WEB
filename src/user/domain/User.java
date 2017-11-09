package user.domain;

public class User {
	private String id;
	private String password;
	private UserInfo userInfo;
	
	public User() {
	}
	
	public User(String subName) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSubName(subName);
		setUserInfo(userInfo);
	}
	
	public User(String id, String password, String name, String email, String subName, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.userInfo = new UserInfo(this, name, email, subName, phoneNumber);
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
		userInfo.setUser(this);
	}
	
}
