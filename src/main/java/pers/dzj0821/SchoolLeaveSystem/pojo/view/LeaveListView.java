package pers.dzj0821.SchoolLeaveSystem.pojo.view;

import java.text.DateFormat;

import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;
import pers.dzj0821.SchoolLeaveSystem.util.DateUtil;

public class LeaveListView {
	private Integer id;
	private User user;
	private String clazzFullName;
	private String leaveTime;
	private String createTime;
	private LeaveType type;
	private User reviewer;
	private String reviewTime;

	public LeaveListView() {}

	public LeaveListView(Integer id, User user, String clazzFullName, String leaveTime, String createTime, LeaveType type, User reviewer, String reviewTime) {
		super();
		this.id = id;
		this.user = user;
		this.clazzFullName = clazzFullName;
		this.leaveTime = leaveTime;
		this.createTime = createTime;
		this.reviewer = reviewer;
		this.reviewTime = reviewTime;
	}

	public LeaveListView(Leave leave) {
		this.id = leave.getId();
		this.user = leave.getUser();
		this.clazzFullName = leave.getClazz().clazzFullName();
		DateFormat format = DateUtil.getDateFormatter();
		this.leaveTime = format.format(leave.getStartDate()) + "第" + leave.getStartLesson() + "节课 至 "
				+ format.format(leave.getEndDate()) + "第" + leave.getEndLesson() + "节课";
		this.createTime = format.format(leave.getCreateTime());
		this.type = leave.getType();
		this.reviewer = leave.getReviewer();
		if(leave.getReviewTime() != null) {
			this.reviewTime = format.format(leave.getReviewTime());
		}
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
	
	public String getClazzFullName() {
		return clazzFullName;
	}

	public void setClazzFullName(String clazzFullName) {
		this.clazzFullName = clazzFullName;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}
}
