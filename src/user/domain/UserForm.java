package user.domain;

public class UserForm extends User{
	
	private String password;
	
	public UserForm() {}
	
	public UserForm(String id, String password, String name, String email, String subName, String phoneNumber){
		setId(id);
		setPassword(password);
		setName(subName);
		setEmail(email);
		setSubName(subName);
		setPhoneNumber(phoneNumber);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
