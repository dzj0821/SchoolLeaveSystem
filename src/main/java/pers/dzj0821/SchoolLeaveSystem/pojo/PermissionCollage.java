package pers.dzj0821.SchoolLeaveSystem.pojo;

public class PermissionCollage {
	//TODO 将基础数据类型替换为包装类
	private int id;
	private User user;
	private Collage collage;
	
	@Override
	public String toString() {
		return "PermissionCollage [id=" + id + ", user=" + user + ", collage=" + collage + "]";
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
	public Collage getCollage() {
		return collage;
	}
	public void setCollage(Collage collage) {
		this.collage = collage;
	}
	
	
}
