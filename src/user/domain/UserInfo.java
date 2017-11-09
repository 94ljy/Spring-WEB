package user.domain;

public class UserInfo {
	private User user;
	private String name;
	private String email;
	private String subName;
	private String phoneNumber;
	
	public UserInfo() {}
	
	public UserInfo(User user) {
		this.user = user;
	}
	
	public UserInfo(User user, String name, String email, String subName, String phoneNumber) {
		this.user = user;
		this.name = name;
		this.email = email;
		this.subName = subName;
		this.phoneNumber = phoneNumber;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
