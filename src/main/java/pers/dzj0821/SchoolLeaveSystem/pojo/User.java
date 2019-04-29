package pers.dzj0821.SchoolLeaveSystem.pojo;

import pers.dzj0821.SchoolLeaveSystem.type.UserType;

public class User {
	private int id;
	private String username;
	private String hex256Password;
	private UserType type;
	private String name;
	private int telephone;
	private String clientToken;
	private String clientId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHex256Password() {
		return hex256Password;
	}
	public void setHex256Password(String hex256Password) {
		this.hex256Password = hex256Password;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getClientToken() {
		return clientToken;
	}
	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
