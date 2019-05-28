package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.LeaveImage;

public interface LeaveImageDao {
	@Insert("insert into leave_image(path, leave_id) values(#{path}, #{leave.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertLeaveImage(LeaveImage leaveImage) throws Exception;
	
	@Select("select  * from leave_image where leave_id = #{leave_id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "leave_id", property = "leave", one = @One(select = "per.dzj0821.SchoolLeaveSystem.dao.LeaveDao.selectLeaveById"))
	})
	public List<LeaveImage> selectLeaveImagesByLeaveId(int leaveId) throws Exception;
}
