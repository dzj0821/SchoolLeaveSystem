package pers.dzj0821.SchoolLeaveSystem.pojo;

public class PermissionCollage {
	private Integer id;
	private User user;
	private Collage collage;
	
	@Override
	public String toString() {
		return "PermissionCollage [id=" + id + ", user=" + user + ", collage=" + collage + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
