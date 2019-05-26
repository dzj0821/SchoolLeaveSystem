package pers.dzj0821.SchoolLeaveSystem.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface LeaveService {
	/**
	 * 创建假条
	 * @param user 申请请假的用户，保证不为null
	 * @param startYear 请假开始年份
	 * @param startMonth 请假开始月份
	 * @param startDay 请假开始日期
	 * @param startLesson 请假开始第x节课
	 * @param endYear 请假结束年份
	 * @param endMonth 请假结束月份
	 * @param endDay 请假结束日期
	 * @param endLesson 请假结束第y节课
	 * @param reason 请假原因
	 * @param images 请假原因附件图片，可能为null
	 * @return 处理结果，可能有<br>
	 * ACCESS_DENIED 用户不为普通用户
	 * INVALID_PARAMS 参数不正确，具体信息由message字段给出
	 * 
	 */
	public JSONResult create(User user, int startYear, int startMonth, int startDay, int startLesson, int endYear, int endMonth, int endDay, int endLesson, String reason, CommonsMultipartFile[] images);
}
