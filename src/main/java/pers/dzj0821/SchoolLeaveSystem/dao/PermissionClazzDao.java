package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.PermissionClazz;

public interface PermissionClazzDao {
	@Select("select * from permission_clazz where user_id = #{user_id} and clazz_id = #{clazz_id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "user_id", property = "user", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
		@Result(column = "clazz_id", property = "clazz", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao.selectClazzById"))
	})
	public PermissionClazz selectPermissionClazzByUserIdAndClazzId(@Param("user_id")Integer userId, @Param("clazz_id")Integer clazzId) throws Exception;
	
	@Select("select * from permission_clazz where user_id = #{user_id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "user_id", property = "user", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.UserDao.selectUserById")),
		@Result(column = "clazz_id", property = "clazz", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao.selectClazzById"))
	})
	public List<PermissionClazz> selectPermissionClazzesByUserId(Integer userId) throws Exception;
}
