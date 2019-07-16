package pers.dzj0821.SchoolLeaveSystem.service;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface MajorService {
	public JSONResult list(User user);
	
	public JSONResult add(Integer collageId, String name, User user);
	
	public JSONResult change(Integer id, String changeName, Integer changeCollageId, User user);
	
	public JSONResult delete(Integer id, User user);
}
