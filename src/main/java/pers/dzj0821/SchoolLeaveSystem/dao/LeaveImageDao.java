package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import pers.dzj0821.SchoolLeaveSystem.pojo.LeaveImage;

public interface LeaveImageDao {
	@Insert("insert into leave_image(path, leave_id) values(#{path}, #{leave.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertLeaveImage(LeaveImage leaveImage) throws Exception;
}
