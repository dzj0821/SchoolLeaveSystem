package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public interface UserDao {
	@Select("select * from user where username = #{id}")
	User selectUserByUsername(String username) throws Exception;
}
