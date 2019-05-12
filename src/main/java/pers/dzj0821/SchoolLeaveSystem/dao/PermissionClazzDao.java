package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.PermissionClazz;

public interface PermissionClazzDao {
	@Select("select * from permission_clazz where user_id = #{user_id} and clazz_id = #{clazz_id}")
	public PermissionClazz selectPermissionClazzByUserIdAndClazzId(@Param("user_id")int userId, @Param("clazz_id")int clazzId);
}
