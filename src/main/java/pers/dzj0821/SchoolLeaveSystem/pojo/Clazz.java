package pers.dzj0821.SchoolLeaveSystem.pojo;

public class Clazz {
	private Integer id;
	private Integer no;
	private Grade grade;
	private Major major;

	public String clazzFullName() {
		//如：2015级网络工程1班
		return grade.getYear() + "级" + major.getCollage().getName() + major.getName() + no + "班";
	}
	
	@Override
	public String toString() {
		return "Class [id=" + id + ", no=" + no + ", grade=" + grade + ", major=" + major + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

}
