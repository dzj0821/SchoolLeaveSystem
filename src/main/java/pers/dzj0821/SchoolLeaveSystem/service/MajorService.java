package pers.dzj0821.SchoolLeaveSystem.service;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface MajorService {
	public JSONResult list(User user);
}
