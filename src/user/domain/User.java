package user.domain;

public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private String subName;
	private String phoneNumber;
	
	public User() {}
	
	public User(String id, String password, String name, String email, String subName, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.subName = subName;
		this.phoneNumber = phoneNumber;
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
