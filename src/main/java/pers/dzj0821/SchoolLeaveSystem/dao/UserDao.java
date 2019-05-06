package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public interface UserDao {
	@Select("select * from user where username = #{username}")
	@Results({
		@Result(column="clazz_id", property="clazz",one=@One(select="pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao.selectClazzById"))
	})
	User selectUserByUsername(String username) throws Exception;
	
	@Insert("insert into user(username, hex256_password, type, name, telephone) values(#{username}, #{hex256Password}, #{type}, #{name}, #{telephone})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insertUser(User user) throws Exception;
}