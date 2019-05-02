package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public interface UserDao {
	@Select("select * from user where username = #{username}")
	User findUserByUsername(String username) throws Exception;
	
	@Insert("insert into user(username, hex256_password, type, name, telephone) values(#{username}, #{hex256Password}, #{type}, #{name}, #{telephone})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int addUser(User user) throws Exception;
}