package pers.dzj0821.SchoolLeaveSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.dzj0821.SchoolLeaveSystem.dao.MajorDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.MajorService;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Service
public class MajorServiceImpl implements MajorService {
	
	@Autowired
	private MajorDao majorDao;
	
	@Override
	public JSONResult list(User user) {
		if(user == null || user.getType().getCode() > UserType.COLLAGE_ADMIN.getCode()) {
			return JSONResult.ACCESS_DENIED;
		}
		return null;
	}
}
