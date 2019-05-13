package pers.dzj0821.SchoolLeaveSystem.pojo;

import java.util.Date;

public class Log {
	//TODO 将基础数据类型替换为包装类
	private int id;
	private User user;
	private String ip;
	private String action;
	private String message;
	private Date time;

	@Override
	public String toString() {
		return "Log [id=" + id + ", user=" + user + ", ip=" + ip + ", action=" + action + ", message=" + message
				+ ", time=" + time + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
