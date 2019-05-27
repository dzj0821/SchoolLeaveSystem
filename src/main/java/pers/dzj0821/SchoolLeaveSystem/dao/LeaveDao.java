package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;

public interface LeaveDao {
	@Insert("insert into leave(user_id, clazz_id, telephone, start_date, start_lesson, "
			+ "end_date, end_lesson, reason, create_time, type) values(#{user.id}, #{clazz.id}, "
			+ "#{user.telephone}, #{startDate}, #{startLesson}, #{endDate}, #{endLesson}, #{reason}, now(), #{type})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertLeave(Leave leave) throws Exception;
}
