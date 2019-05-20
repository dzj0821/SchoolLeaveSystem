package pers.dzj0821.SchoolLeaveSystem.pojo.json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;

public class JSONResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	private static final String CODE_KEY_NAME = "code";
	public static final JSONResult SERVER_ERROR = new JSONResult(JSONCodeType.SERVER_ERROR, Messages.getString("ServerError"), null); //$NON-NLS-1$

	public JSONResult(JSONCodeType codeType, String message, Map<String, Object> data) {
		put(CODE_KEY_NAME, codeType);
		put("message", message);
		put("data", data);
	}
	
	public JSONCodeType getCode() {
		return (JSONCodeType) get(CODE_KEY_NAME);
	}
	
	//意义不明的重写
	/*
	@Override
	public int hashCode() {
		Object code = get("code"), message = get("message"), data = get("data");
		return (code == null ? 0 : code.hashCode()) + (message == null ? 0 : message.hashCode()) + (data == null ? 0 : data.hashCode());
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	*/

	
	/**
	 * 重写方法，使code、message和data以外的元素对前端不可见
	 */
	@Override
	public Set<Entry<String, Object>> entrySet() {
		HashSet<Entry<String, Object>> set = new HashSet<Entry<String, Object>>();
		Set<Entry<String, Object>> entries = super.entrySet();
		for (Entry<String, Object> entry : entries) {
			if (entry.getKey().equals("code") || entry.getKey().equals("message") || entry.getKey().equals("data")) {
				set.add(entry);
			}
		}
		return set;
	}
	
}
