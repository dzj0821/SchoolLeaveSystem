package pers.dzj0821.SchoolLeaveSystem.service;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface ClazzService {
	public JSONResult list(User user);
	
	public JSONResult info(Integer id, User user);
}
