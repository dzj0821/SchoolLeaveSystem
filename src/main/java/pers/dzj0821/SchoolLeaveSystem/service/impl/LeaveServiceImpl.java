package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.LeaveService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Override
	public JSONResult create(User user, int startYear, int startMonth, int startDay, int startLesson, int endYear,
			int endMonth, int endDay, int endLesson, String reason, CommonsMultipartFile[] images) {
		//不是普通用户不能请假
		if(user.getType() != UserType.NORMAL_USER) {
			return JSONResult.ACCESS_DENIED;
		}
		//获取日历对象，通过日历对象获取当前日期
		Calendar currentCalendar = Calendar.getInstance();
		//只有年月日用于比较的Calendar
		Calendar currentCompareCalendar = Calendar.getInstance();
		currentCompareCalendar.clear();
		//设置为当前日期
		currentCompareCalendar.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DATE));
		//请假开始日期的Calendar
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.clear();
		startCalendar.set(startYear, startMonth, startDay);
		//请假结束日期的Calendar
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.clear();
		endCalendar.set(endYear, endMonth, endDay);
		//请假开始日期和截止日期都必须在申请日期之后，且截止日期不能在开始日期之前
		if(currentCompareCalendar.compareTo(startCalendar) < 0 || currentCompareCalendar.compareTo(endCalendar) < 0) {
			//TODO 外部化字符串
			return new JSONResult(JSONCodeType.INVALID_PARAMS, "请假开始和截止日期不能小于今天", null);
		}
		//如果截止日期在开始日期前 或 截止日期的第X节课小于开始日期的第Y节课
		if(endCalendar.compareTo(startCalendar) < 0 || (endCalendar.compareTo(startCalendar) == 0 && endLesson <= startLesson)) {
			return new JSONResult(JSONCodeType.INVALID_PARAMS, "截止日期必须大于开始日期", null);
		}
		//验证部分结束
		//TODO 完成后续功能
		return null;
	}

}
