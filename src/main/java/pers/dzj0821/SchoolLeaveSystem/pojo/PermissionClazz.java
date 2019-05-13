package pers.dzj0821.SchoolLeaveSystem.pojo;

public class PermissionClazz {
	//TODO 将基础数据类型替换为包装类
	private int id;
	private User user;
	private Clazz clazz;

	@Override
	public String toString() {
		return "PermissionClazz [id=" + id + ", user=" + user + ", clazz=" + clazz + "]";
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

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

}
