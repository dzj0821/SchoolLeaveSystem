package pers.dzj0821.SchoolLeaveSystem.service;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface ClazzService {
	public JSONResult list(User user) throws Exception;
	
	public JSONResult info(Integer id, User user);
	
	public JSONResult add(Integer no, Integer gradeId, Integer majorId, User user) throws Exception;
	
	public JSONResult change(Integer id, Integer no, Integer gradeId, Integer majorId, User user) throws Exception;
	
	public JSONResult delete(Integer id, User user) throws Exception;
	
	public JSONResult addUser(Integer id, String username, User user) throws Exception;
	
	public JSONResult removeUser(Integer id, Integer userId, User user) throws Exception;
}
