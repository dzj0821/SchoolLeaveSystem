package pers.dzj0821.SchoolLeaveSystem.service;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface GradeService {
	public JSONResult list(User user);
	
	public JSONResult add(Integer grade, User user);
	
	public JSONResult delete(Integer id, User user);
}
