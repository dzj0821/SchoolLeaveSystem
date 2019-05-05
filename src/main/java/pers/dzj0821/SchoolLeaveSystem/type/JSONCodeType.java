package pers.dzj0821.SchoolLeaveSystem.type;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum JSONCodeType {
	Success,
	RegisterUsernameInvalid,
	RegisterNameInvalid,
	RegisterTelephoneInvalid,
	RegisterPasswordInvalid,
	ServerError,
	RegisterUsernameAlreadyExist,
	SessionTimeout
}
