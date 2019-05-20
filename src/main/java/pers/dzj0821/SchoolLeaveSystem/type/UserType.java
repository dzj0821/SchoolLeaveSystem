package pers.dzj0821.SchoolLeaveSystem.type;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum UserType {
	SUPER_ADMIN,
	COLLAGE_ADMIN,
	CLAZZ_ADMIN,
	NORMAL_USER
}
