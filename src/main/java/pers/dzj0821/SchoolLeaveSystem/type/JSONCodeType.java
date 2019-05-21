package pers.dzj0821.SchoolLeaveSystem.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JSONCodeType {
	SUCCESS(100),
	
	INVALID_USERNAME(200),
	INVALID_NAME(201),
	INVALID_TELEPHONE(202),
	INVALID_PASSWORD(203),
	INVALID_PARAMS(204),
	
	SERVER_ERROR(300),
	
	REGISTER_USERNAME_ALREADY_EXIST(400),
	SESSION_TIMEOUT(401),
	USER_NOT_FOUND(402),
	USERNAME_OR_PASSWORD_ERROR(403),
	ACCESS_DENIED(404),
	OLD_PASSWORD_ERROR(405),
	;
	
	private int code;
	private JSONCodeType(int code) {
		this.code = code;
	}
	@JsonValue
	public int getCode() {
		return code;
	}
}
