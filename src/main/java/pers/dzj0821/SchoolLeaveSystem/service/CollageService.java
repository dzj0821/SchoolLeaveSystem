package pers.dzj0821.SchoolLeaveSystem.service;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface CollageService {
	public JSONResult list(User user);
	
	public JSONResult add(String name, User user);
	
	public JSONResult change(Integer id, String name, User user);
	
	public JSONResult delete(Integer id, User user);
}
