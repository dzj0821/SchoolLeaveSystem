package pers.dzj0821.SchoolLeaveSystem.type;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum UserType {
	SUPER_ADMIN("超级管理员"),
	COLLAGE_ADMIN("学院管理员"),
	CLAZZ_ADMIN("班级管理员"),
	NORMAL_USER("普通用户");
	
	private String name;
	
	private UserType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
