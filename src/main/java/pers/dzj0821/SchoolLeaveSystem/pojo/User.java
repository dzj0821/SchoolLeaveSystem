package pers.dzj0821.SchoolLeaveSystem.pojo;

import pers.dzj0821.SchoolLeaveSystem.type.UserType;

public class User {
	private int id;
	private String username;
	//TODO 更新名称
	private String hex256Password;
	private UserType type;
	private String name;
	private String telephone;
	private Clazz clazz;
	private String clientToken;
	private String clientId;

	public User() {
	}

	public User(String username, String hex256Password, String name, String telephone) {
		id = 0;
		this.username = username;
		this.hex256Password = hex256Password;
		type = UserType.Normal;
		this.name = name;
		this.telephone = telephone;
		clientToken = null;
		clientId = null;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", hex256Password=" + hex256Password + ", type=" + type
				+ ", name=" + name + ", telephone=" + telephone + ",clazz=" + clazz + ", clientToken=" + clientToken + ", clientId="
				+ clientId + "]";
	}

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
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
