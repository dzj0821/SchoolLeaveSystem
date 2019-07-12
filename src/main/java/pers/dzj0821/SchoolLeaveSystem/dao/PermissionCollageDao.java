package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.PermissionCollage;

public interface PermissionCollageDao {
	@Select("select * from permission_collage where user_id = #{user_id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "user_id", property = "user", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
		@Result(column = "collage_id", property = "collage", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.CollageDao.selectCollageById"))
	})
	public List<PermissionCollage> selectPermissionCollagesByUserId(int userId) throws Exception;
	
	@Select("select * from permission_collage where user_id = #{user_id} and collage_id = #{collage_id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "user_id", property = "user", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
		@Result(column = "collage_id", property = "collage", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.CollageDao.selectCollageById"))
	})
	public PermissionCollage selectPermissionCollageByUserIdAndCollageId(@Param("user_id") int userId, @Param("collage_id") int collageId) throws Exception;
}
