package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;

public interface LeaveDao {
	@Insert("insert into `leave`(user_id, clazz_id, telephone, start_date, start_lesson, "
			+ "end_date, end_lesson, reason, create_time, type) values(#{user.id}, #{clazz.id}, "
			+ "#{user.telephone}, #{startDate}, #{startLesson}, #{endDate}, #{endLesson}, #{reason}, now(), #{type})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertLeave(Leave leave) throws Exception;
	
	@Select("select * from `leave` where id = #{id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "user_id", property = "user", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
		@Result(column = "clazz_id", property = "clazz", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao.selectClazzById")),
		@Result(column = "reviewer_id", property = "reviewer", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
		@Result(column = "id", property = "leaveImages", many = @Many(select = "pers.dzj0821.SchoolLeaveSystem.dao.LeaveImageDao.selectLeaveImagesByLeaveId"))
	})
	public Leave selectLeaveById(int id) throws Exception;
	
	public int updateLeaveById(Leave leave) throws Exception;
}
