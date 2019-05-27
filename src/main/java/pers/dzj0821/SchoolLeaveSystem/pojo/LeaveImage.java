package pers.dzj0821.SchoolLeaveSystem.pojo;

public class LeaveImage {
	private int id;
	private String path;
	private Leave leave;
	
	public LeaveImage() {}
	
	public LeaveImage(int id, String path, Leave leave) {
		this.id = id;
		this.path = path;
		this.leave = leave;
	}
	
	@Override
	public String toString() {
		return "LeaveImage [id=" + id + ", path=" + path + ", leave=" + leave + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Leave getLeave() {
		return leave;
	}
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
}
