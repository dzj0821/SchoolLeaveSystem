package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.dzj0821.SchoolLeaveSystem.dao.CollageDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.Collage;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.CollageService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Service
public class CollageServiceImpl implements CollageService {
	
	@Autowired
	private CollageDao collageDao;
	
	private Logger logger = LogManager.getLogger(CollageServiceImpl.class);

	@Override
	public JSONResult list(User user) {
		if(user == null || user.getType().getCode() > UserType.SUPER_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		List<Collage> collages = null;
		try {
			collages = collageDao.selectCollages();
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("collages", collages);
		return new JSONResult(JSONCodeType.SUCCESS, null, map);
	}
	
	@Override
	public JSONResult add(String name, User user) {
		if(user == null || user.getType().getCode() > UserType.SUPER_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		try {
			collageDao.insertCollage(name);
		} catch (Exception e) {
			logger.warn(e);
			return JSONResult.SERVER_ERROR;
		}
		return new JSONResult(JSONCodeType.SUCCESS, "添加成功", null);
	}
}
