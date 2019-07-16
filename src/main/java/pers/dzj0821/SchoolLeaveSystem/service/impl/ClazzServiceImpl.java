package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao;
import pers.dzj0821.SchoolLeaveSystem.dao.CollageDao;
import pers.dzj0821.SchoolLeaveSystem.dao.MajorDao;
import pers.dzj0821.SchoolLeaveSystem.dao.PermissionClazzDao;
import pers.dzj0821.SchoolLeaveSystem.dao.PermissionCollageDao;
import pers.dzj0821.SchoolLeaveSystem.dao.UserDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.Clazz;
import pers.dzj0821.SchoolLeaveSystem.pojo.Major;
import pers.dzj0821.SchoolLeaveSystem.pojo.PermissionClazz;
import pers.dzj0821.SchoolLeaveSystem.pojo.PermissionCollage;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.ClazzService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Service
public class ClazzServiceImpl implements ClazzService {
	@Autowired
	private ClazzDao clazzDao;
	@Autowired
	private MajorDao majorDao;
	@Autowired
	private PermissionCollageDao permissionCollageDao;
	@Autowired
	private PermissionClazzDao permissionClazzDao;
	@Autowired
	private UserDao userDao;

	private Logger logger = LogManager.getLogger(ClazzServiceImpl.class);

	@Override
	public JSONResult list(User user) {
		if (user == null || user.getType().getCode() > UserType.CLAZZ_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		// 每个专业和班级的映射
		Map<Major, List<Clazz>> map = new HashMap<Major, List<Clazz>>();
		// 记录用户管理的专业
		List<Major> majors = null;
		if (user.getType() == UserType.SUPER_ADMIN) {
			// 是超级管理员，选取所有的专业
			try {
				majors = majorDao.selectMajors();
			} catch (Exception e) {
				logger.warn(e);
				return JSONResult.SERVER_ERROR;
			}
		}
		if (user.getType() == UserType.COLLAGE_ADMIN) {
			majors = new ArrayList<Major>();
			// 院级管理员 根据权限选取学院
			List<PermissionCollage> permissionCollages = null;
			try {
				permissionCollages = permissionCollageDao.selectPermissionCollagesByUserId(user.getId());
			} catch (Exception e) {
				logger.warn(e);
				return JSONResult.SERVER_ERROR;
			}
			for (PermissionCollage permissionCollage : permissionCollages) {
				// 将所有拥有权限的学院再取班级
				try {
					majors.addAll(majorDao.selectMajorsByCollageId(permissionCollage.getCollage().getId()));
				} catch (Exception e) {
					logger.warn(e);
					return JSONResult.SERVER_ERROR;
				}
			}
		}
		if (user.getType() == UserType.CLAZZ_ADMIN) {
			majors = new ArrayList<Major>();
			// 院级管理员 根据权限选取学院
			List<PermissionClazz> permissionClazzs = null;
			try {
				permissionClazzs = permissionClazzDao.selectPermissionClazzesByUserId(user.getId());
			} catch (Exception e) {
				logger.warn(e);
				return JSONResult.SERVER_ERROR;
			}
			// 用set去重
			Set<Major> majorSet = new HashSet<Major>();
			for (PermissionClazz permissionClazz : permissionClazzs) {
				// 获取管理班级所属专业
				Major major = permissionClazz.getClazz().getMajor();
				majorSet.add(major);
			}
			majors.addAll(majorSet);
		}
		for (Major major : majors) {
			// 对于每个专业查询所拥有的班级，形成映射
			List<Clazz> clazzs = null;
			try {
				clazzs = clazzDao.selectClazzesByMajorId(major.getId());
			} catch (Exception e) {
				logger.warn(e);
				return JSONResult.SERVER_ERROR;
			}
			map.put(major, clazzs);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("map", map);
		data.put("majors", majors);
		return new JSONResult(JSONCodeType.SUCCESS, null, data);
	}

	@Override
	public JSONResult info(Integer id, User user) {
		if (user == null || user.getType().getCode() > UserType.CLAZZ_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		if (id == null) {
			return new JSONResult(JSONCodeType.INVALID_PARAMS, null, null);
		}
		Clazz clazz = null;
		try {
			clazz = clazzDao.selectClazzById(id);
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		if (clazz == null) {
			return new JSONResult(JSONCodeType.DATA_NOT_FOUND, "班级不存在", null);
		}
		//是否有权限
		boolean success = false;
		if(user.getType() == UserType.SUPER_ADMIN) {
			success = true;
		}
		if (!success && user.getType().getCode() <= UserType.COLLAGE_ADMIN.getCode()) {
			PermissionCollage permissionCollage = null;
			try {
				permissionCollage = permissionCollageDao
						.selectPermissionCollageByUserIdAndCollageId(user.getId(), clazz.getMajor().getCollage().getId());
			} catch (Exception e) {
				logger.warn(e);
				return JSONResult.SERVER_ERROR;
			}
			if(permissionCollage != null) {
				success = true;
			}
		}
		if(!success && user.getType().getCode() <= UserType.CLAZZ_ADMIN.getCode()) {
			PermissionClazz permissionClazz = null;
			try {
				permissionClazz = permissionClazzDao.selectPermissionClazzByUserIdAndClazzId(user.getId(), clazz.getId());
			} catch (Exception e) {
				logger.warn(e);
				return JSONResult.SERVER_ERROR;
			}
			if(permissionClazz != null) {
				success = true;
			}
		}
		if(!success) {
			return JSONResult.ACCESS_DENIED;
		}
		List<User> users = null;
		try {
			users = userDao.selectUsersByClazzId(id);
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", users);
		return new JSONResult(JSONCodeType.SUCCESS, null, map);
	}
}
