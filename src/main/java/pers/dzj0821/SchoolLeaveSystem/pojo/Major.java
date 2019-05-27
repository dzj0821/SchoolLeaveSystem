package pers.dzj0821.SchoolLeaveSystem.pojo;

public class Major {
	private int id;
	private String name;
	private Collage collage;

	@Override
	public String toString() {
		return "Major [id=" + id + ", name=" + name + ", collage=" + collage + "]";
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

	public Collage getCollage() {
		return collage;
	}

	public void setCollage(Collage collage) {
		this.collage = collage;
	}

}
