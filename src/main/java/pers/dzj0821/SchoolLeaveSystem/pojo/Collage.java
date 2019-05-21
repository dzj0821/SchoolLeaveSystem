package pers.dzj0821.SchoolLeaveSystem.pojo;

public class Collage {
	//TODO 将基础数据类型替换为包装类
	private int id;
	private String name;

	@Override
	public String toString() {
		return "Collage [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
