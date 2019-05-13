package pers.dzj0821.SchoolLeaveSystem.type;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum JSONCodeType {
	SUCCESS,
	INVALID_USERNAME,
	INVALID_NAME,
	INVALID_TELEPHONE,
	INVALID_PASSWORD,
	SERVER_ERROR,
	REGISTER_USERNAME_ALREADY_EXIST,
	SESSION_TIMEOUT,
	USER_NOT_FOUND,
	USERNAME_OR_PASSWORD_ERROR,
	ACCESS_DENIED,
	OLD_PASSWORD_ERROR,
	INVALID_PARAMS
}
