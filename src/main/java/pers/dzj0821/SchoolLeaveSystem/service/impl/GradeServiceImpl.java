package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.dzj0821.SchoolLeaveSystem.dao.GradeDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.Grade;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.GradeService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;

	private Logger logger = LogManager.getLogger(GradeServiceImpl.class);

	@Override
	public JSONResult list(User user) {
		// 如果未登录 或 权限低于超级管理员
		if (user == null || user.getType().getCode() > UserType.SUPER_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		List<Grade> grades = null;
		try {
			grades = gradeDao.selectGrades();
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grades", grades);
		return new JSONResult(JSONCodeType.SUCCESS, null, map);
	}

	@Override
	public JSONResult add(Integer grade, User user) {
		// 如果未登录 或 权限低于超级管理员
		if (user == null || user.getType().getCode() > UserType.SUPER_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		if(grade == null || grade < 1000 || grade > 10000) {
			return new JSONResult(JSONCodeType.INVALID_PARAMS, "参数错误", null);
		}
		try {
			gradeDao.insertGrade(grade);
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		return new JSONResult(JSONCodeType.SUCCESS, "添加成功", null);
	}
}
