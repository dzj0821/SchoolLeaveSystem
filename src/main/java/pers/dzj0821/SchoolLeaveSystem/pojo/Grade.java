package pers.dzj0821.SchoolLeaveSystem.pojo;

public class Grade {
	//TODO 将基础数据类型替换为包装类
	private int id;
	private int year;

	@Override
	public String toString() {
		return "Grade [id=" + id + ", year=" + year + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
