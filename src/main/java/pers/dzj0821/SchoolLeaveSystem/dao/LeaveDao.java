package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;

import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;

public interface LeaveDao {
	@Insert("insert into `leave`(user_id, clazz_id, telephone, start_date, start_lesson, "
			+ "end_date, end_lesson, reason, create_time, type) values(#{user.id}, #{clazz.id}, "
			+ "#{user.telephone}, #{startDate}, #{startLesson}, #{endDate}, #{endLesson}, #{reason}, now(), #{type})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertLeave(Leave leave) throws Exception;

	@Select("select * from `leave` where id = #{id}")
	@Results({ @Result(column = "id", property = "id", id = true),
			@Result(column = "user_id", property = "user", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
			@Result(column = "clazz_id", property = "clazz", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao.selectClazzById")),
			@Result(column = "reviewer_id", property = "reviewer", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
			@Result(column = "id", property = "leaveImages", many = @Many(select = "pers.dzj0821.SchoolLeaveSystem.dao.LeaveImageDao.selectLeaveImagesByLeaveId", fetchType = FetchType.LAZY)) })
	public Leave selectLeaveById(int id) throws Exception;

	@UpdateProvider(type = LeaveProvider.class, method = "updateLeaveById")
	public int updateLeaveById(Leave leave) throws Exception;
	
	@SelectProvider(type = LeaveProvider.class, method = "selectLeaveByLeave")
	public List<Leave> selectLeaveByLeave(Leave leave) throws Exception;

	class LeaveProvider {
		public String updateLeaveById(Leave leave) {
			return new SQL() {{
				UPDATE("`leave`");
				if(leave.getUser() != null) {
					SET("user_id = #{user.id}");
				}
				if(leave.getClazz() != null) {
					SET("clazz_id = #{clazz.id}");
				}
				if(leave.getTelephone() != null) {
					SET("telephone = #{telephone}");
				}
				if(leave.getStartDate() != null) {
					SET("start_date = #{startDate}");
				}
				if(leave.getStartLesson() != null) {
					SET("start_lesson = #{startLesson}");
				}
				if(leave.getEndDate() != null) {
					SET("end_date = #{endDate}");
				}
				if(leave.getEndLesson() != null) {
					SET("end_lesson = #{endLesson}");
				}
				if(leave.getReason() != null) {
					SET("reason = #{reason}");
				}
				if(leave.getCreateTime() != null) {
					SET("create_time = #{createTime}");
				}
				if(leave.getType() != null) {
					SET("type = #{type}");
				}
				if(leave.getReviewer() != null) {
					SET("reviewer_id = #{reviewer.id}");
				}
				if(leave.getReviewTime() != null) {
					SET("review_time = #{reviewTime}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectLeaveByLeave(Leave leave) {
			return new SQL() {{
				SELECT("*");
				FROM("`leave`");
				//TODO 未完成
			}}.toString();
		}
	}
}
