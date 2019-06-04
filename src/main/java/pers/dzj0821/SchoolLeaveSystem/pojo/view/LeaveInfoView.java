package pers.dzj0821.SchoolLeaveSystem.pojo.view;
import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;

import java.text.DateFormat;
import java.util.List;
import pers.dzj0821.SchoolLeaveSystem.util.DateUtil;
import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public class LeaveInfoView {
	private Integer id;
	private User user;
	private String clazzFullName;
	private String telephone;
	private String leaveTime;
    private String reason;
    private List<String> imgUrl;
    private LeaveType type;
    private User reviewer;
    private String reviewTime;
	private String createTime;
    
    public LeaveInfoView(Integer id, User user,String telephone, String clazzFullName,String reason,List<String> imgUrl, String leaveTime, String createTime, LeaveType type, User reviewer, String reviewTime) {
        super();
        this.id = id;
        this.user = user;
        this.clazzFullName = clazzFullName;
        this.leaveTime = leaveTime;
        this.createTime = createTime;
        this.reviewer = reviewer;
        this.reviewTime = reviewTime;
        this.telephone=telephone;
        this.reason=reason;
        this.imgUrl=imgUrl;
    }
    public LeaveInfoView(Leave leave) {
        this.id = leave.getId();
        this.user = leave.getUser();
        this.telephone=leave.getTelephone();
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
        this.reason=leave.getReason();
        this.imgUrl=leave.getImgUrl();
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<String> getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(List<String> imgUrl) {
		this.imgUrl = imgUrl;
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

	@Override
	public String toString() {
		return "LeaveInfoView [id=" + id + ", user=" + user + ", clazzFullName=" + clazzFullName + ", telephone="
				+ telephone + ", leaveTime=" + leaveTime + ", reason=" + reason + ", imgUrl=" + imgUrl + ", type="
				+ type + ", reviewer=" + reviewer + ", reviewTime=" + reviewTime + ", createTime=" + createTime + "]";
	}
    
    
}
