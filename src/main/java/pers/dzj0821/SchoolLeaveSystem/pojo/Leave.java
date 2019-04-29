package pers.dzj0821.SchoolLeaveSystem.pojo;

import java.util.Date;

import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;

public class Leave {
	private int id;
	private User user;
	private String clazzName;
	private int telephone;
	private Date startDate;
	private int startLesson;
	private Date endDate;
	private int endLesson;
	private String reason;
	private Date createTime;
	private LeaveType type;
	private User reviewer;
	private Date reviewTime;

	@Override
	public String toString() {
		return "Leave [id=" + id + ", user=" + user + ", clazzName=" + clazzName + ", telephone=" + telephone
				+ ", startDate=" + startDate + ", startLesson=" + startLesson + ", endDate=" + endDate + ", endLesson="
				+ endLesson + ", reason=" + reason + ", createTime=" + createTime + ", type=" + type + ", reviewer="
				+ reviewer + ", reviewTime=" + reviewTime + "]";
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

	public String getClassName() {
		return clazzName;
	}

	public void setClassName(String className) {
		this.clazzName = className;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStartLesson() {
		return startLesson;
	}

	public void setStartLesson(int startLesson) {
		this.startLesson = startLesson;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getEndLesson() {
		return endLesson;
	}

	public void setEndLesson(int endLesson) {
		this.endLesson = endLesson;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public LeaveType getType() {
		return type;
	}

	public void setType(LeaveType type) {
		this.type = type;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
}
