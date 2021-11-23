package com.railwayreservation.userservice.model;

public class UserModel {

	private String userName;
	private String email;
	private String password;
	private String role;

	public UserModel() {

	}

	public UserModel(String userName, String email, String password, String role) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public UserModel(UserModel user) {
		this.userName = user.userName;
		this.email = user.email;
		this.password = user.password;
		this.role = user.role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
