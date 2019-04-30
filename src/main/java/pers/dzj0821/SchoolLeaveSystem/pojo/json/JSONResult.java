package pers.dzj0821.SchoolLeaveSystem.pojo.json;

import java.util.HashMap;
import java.util.Map;

public class JSONResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public JSONResult(JSONCode code, String message, Map<String, Object> data) {
		put("code", code.ordinal());
		put("message", message);
		put("data", data);
	}
	
}
