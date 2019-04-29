package pers.dzj0821.SchoolLeaveSystem.pojo;

public class Clazz {
	private int id;
	private int no;
	private Grade grade;
	private Major major;

	@Override
	public String toString() {
		return "Class [id=" + id + ", no=" + no + ", grade=" + grade + ", major=" + major + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
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
