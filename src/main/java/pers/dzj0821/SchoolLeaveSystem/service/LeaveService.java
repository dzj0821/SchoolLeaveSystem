package pers.dzj0821.SchoolLeaveSystem.service;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface LeaveService {
	public JSONResult create(User user, Date startDate, Integer startLesson, Date endDate, Integer endLesson, String reason, CommonsMultipartFile image);
}
