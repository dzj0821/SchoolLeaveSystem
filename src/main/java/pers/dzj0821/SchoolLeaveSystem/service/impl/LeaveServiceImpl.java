package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.dzj0821.SchoolLeaveSystem.dao.LeaveDao;
import pers.dzj0821.SchoolLeaveSystem.dao.LeaveImageDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;
import pers.dzj0821.SchoolLeaveSystem.pojo.LeaveImage;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.LeaveService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;
import pers.dzj0821.SchoolLeaveSystem.util.SHA256Util;

@Service
public class LeaveServiceImpl implements LeaveService {
	private Logger logger = LogManager.getLogger(LeaveServiceImpl.class);

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private LeaveImageDao leaveImageDao;

	@Override
	public JSONResult create(User user, int startYear, int startMonth, int startDay, int startLesson, int endYear,
			int endMonth, int endDay, int endLesson, String reason, CommonsMultipartFile[] images, String systemPath) {
		// 不是普通用户不能请假
		if (user.getType() != UserType.NORMAL_USER) {
			return JSONResult.ACCESS_DENIED;
		}
		// 必须加入班级才能请假
		if (user.getClazz() == null) {
			return new JSONResult(JSONCodeType.ACCESS_DENIED, "必须加入班级才能请假", null);
		}
		// 获取日历对象，通过日历对象获取当前日期
		Calendar currentCalendar = Calendar.getInstance();
		int currentYear = currentCalendar.get(Calendar.YEAR);
		int currentMonth = currentCalendar.get(Calendar.MONTH);
		int currentDay = currentCalendar.get(Calendar.DATE);
		// 这个月的最后一天
		int lastDay = currentCalendar.getActualMaximum(Calendar.DATE);
		// 验证日期
		// 因为Calendar的月份从0开始，所以startMonth和endMonth减1
		//TODO 优化判断流程
		if (startYear < currentYear || startYear >= currentYear + 3 || startMonth - 1 < currentMonth || startMonth > 12
				|| startDay < currentDay || startDay > lastDay || endYear < currentYear || endYear >= currentYear + 3
				|| endMonth - 1 < currentMonth || endMonth > 12 || endDay < currentDay || endDay > lastDay) {
			return new JSONResult(JSONCodeType.INVALID_PARAMS, "日期不符合要求", null);
		}
		// 只有年月日用于比较的Calendar
		Calendar currentCompareCalendar = Calendar.getInstance();
		currentCompareCalendar.clear();
		// 设置为当前日期
		currentCompareCalendar.set(currentYear, currentMonth, currentDay);
		// 请假开始日期的Calendar
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.clear();
		startCalendar.set(startYear, startMonth - 1, startDay);
		// 请假结束日期的Calendar
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.clear();
		endCalendar.set(endYear, endMonth - 1, endDay);
		// 请假开始日期和截止日期都必须在申请日期之后，且截止日期不能在开始日期之前
		if (currentCompareCalendar.compareTo(startCalendar) > 0 || currentCompareCalendar.compareTo(endCalendar) > 0) {
			// TODO 外部化字符串
			return new JSONResult(JSONCodeType.INVALID_PARAMS, "请假开始和截止日期不能小于今天", null);
		}
		// 如果截止日期在开始日期前 或 截止日期的第X节课小于开始日期的第Y节课
		if (endCalendar.compareTo(startCalendar) < 0
				|| (endCalendar.compareTo(startCalendar) == 0 && endLesson <= startLesson)) {
			return new JSONResult(JSONCodeType.INVALID_PARAMS, "截止日期必须大于开始日期", null);
		}
		if (images != null) {
			for (int i = 0; i < images.length; i++) {
				// 文件是否为空
				if (images[i].isEmpty()) {
					return new JSONResult(JSONCodeType.INVALID_PARAMS, "上传文件为空", null);
				}
				// 文件后缀必须为jpg或png
				String originalFilename = images[i].getOriginalFilename();
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".png")) {
					return new JSONResult(JSONCodeType.INVALID_PARAMS, "文件类型必须为jpg或png格式", null);
				}
				// 限制上传大小10M
				if (images[i].getSize() > 10 * 1024 * 1024) {
					return new JSONResult(JSONCodeType.INVALID_PARAMS, "单个图片大小不能超过10M", null);
				}

			}
		}
		// 验证部分结束
		// 转换日期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Formatter startDateFormatter = new Formatter();
		String startDateString = startDateFormatter.format("%04d-%02d-%02d", startYear, startMonth, startDay).toString();
		startDateFormatter.close();
		//Formatter不能重复使用
		Formatter endDateFormatter = new Formatter();
		String endDateString = endDateFormatter.format("%04d-%02d-%02d", endYear, endMonth, endDay).toString();
		endDateFormatter.close();
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = dateFormat.parse(startDateString);
			endDate = dateFormat.parse(endDateString);
		} catch (ParseException e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		// 记录请假申请
		Leave leave = new Leave(0, user, user.getClazz(), user.getTelephone(), startDate, startLesson, endDate,
				endLesson, reason, null, LeaveType.WAIT, null, null, null);
		try {
			int id = leaveDao.insertLeave(leave);
			leave.setId(id);
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		// 保存图片
		if (images != null) {
			for (int i = 0; i < images.length; i++) {
				// 根据请假的id和图片序号改变文件名
				String imageName = SHA256Util
						.encrypt(SHA256Util.encrypt(Integer.toString(leave.getId())) + Integer.toString(i));
				//保存在upload目录下
				String path = "upload/";
				String originalFilename = images[i].getOriginalFilename();
				if (originalFilename.endsWith(".jpg")) {
					path += imageName + ".jpg";
				} else {
					path = imageName + ".png";
				}
				File file = new File(systemPath, path);
				// 如果目录不存在且创建目录失败
				if (!file.exists() && !file.mkdirs()) {
					logger.warn("创建目录失败");
					return JSONResult.SERVER_ERROR;
				}
				try {
					// 保存文件
					images[i].transferTo(file);
				} catch (IllegalStateException | IOException e) {
					logger.warn(e);
					return JSONResult.SERVER_ERROR;
				}
				LeaveImage leaveImage = new LeaveImage(0, path, leave);
				// 插入数据库
				try {
					leaveImageDao.insertLeaveImage(leaveImage);
				} catch (Exception e) {
					logger.warn(e);
					return JSONResult.SERVER_ERROR;
				}
			}
		}
		return new JSONResult(JSONCodeType.SUCCESS, "申请成功，等待管理员审核", null);
	}
}
