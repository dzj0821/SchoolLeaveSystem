package pers.dzj0821.SchoolLeaveSystem.pojo;

import java.util.Date;
import java.util.List;

import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;

public class Leave {
	// TODO 将基础数据类型替换为包装类
	private int id;
	private User user;
	private Clazz clazz;
	private String telephone;
	private Date startDate;
	private int startLesson;
	private Date endDate;
	private int endLesson;
	private String reason;
	private Date createTime;
	private LeaveType type;
	private User reviewer;
	private Date reviewTime;
	private List<LeaveImage> leaveImages;

	public Leave() {
	}

	public Leave(int id, User user, Clazz clazz, String telephone, Date startDate, int startLesson, Date endDate,
			int endLesson, String reason, Date createTime, LeaveType type, User reviewer, Date reviewTime,
			List<LeaveImage> leaveImages) {
		this.id = id;
		this.user = user;
		this.clazz = clazz;
		this.telephone = telephone;
		this.startDate = startDate;
		this.startLesson = startLesson;
		this.endDate = endDate;
		this.endLesson = endLesson;
		this.reason = reason;
		this.createTime = createTime;
		this.type = type;
		this.reviewer = reviewer;
		this.reviewTime = reviewTime;
		this.leaveImages = leaveImages;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", user=" + user + ", clazz=" + clazz + ", telephone=" + telephone + ", startDate="
				+ startDate + ", startLesson=" + startLesson + ", endDate=" + endDate + ", endLesson=" + endLesson
				+ ", reason=" + reason + ", createTime=" + createTime + ", type=" + type + ", reviewer=" + reviewer
				+ ", reviewTime=" + reviewTime + "]";
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
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

	public List<LeaveImage> getLeaveImages() {
		return leaveImages;
	}

	public void setLeaveImages(List<LeaveImage> leaveImages) {
		this.leaveImages = leaveImages;
	}
}
