package pers.dzj0821.SchoolLeaveSystem.pojo.view;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

public class UserInfoView {
	private Integer id;
	private String username;
	private UserType type;
	private String name;
	private String telephone;
	private String clazzFullName;
	
	public UserInfoView() {}
	
	public UserInfoView(User user) {
		id = user.getId();
		username = user.getUsername();
		type = user.getType();
		name = user.getName();
		telephone = user.getTelephone();
		if(user.getClazz() != null) {
			//FIXME NPE
			clazzFullName = user.getClazz().clazzFullName();
		} else {
			clazzFullName = null;
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getClazzFullName() {
		return clazzFullName;
	}
	public void setClazzFullName(String clazzFullName) {
		this.clazzFullName = clazzFullName;
	}
}
