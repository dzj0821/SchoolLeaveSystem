package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public interface UserDao {
	@Select("select * from user where username = #{username}")
	@Results({
		@Result(column="id", property="id", id=true),
		@Result(column="clazz_id", property="clazz",one=@One(select="pers.dzj0821.SchoolLeaveSystem.dao.ClazzDao.selectClazzById"))
	})
	User selectUserByUsername(String username) throws Exception;
	
	@Insert("insert into user(username, password, type, name, telephone) values(#{username}, #{password}, #{type}, #{name}, #{telephone})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insertUser(User user) throws Exception;
	
	@UpdateProvider(type=UserDaoProvider.class, method="updateUserById")
	int updateUserById(User user) throws Exception;
	
	class UserDaoProvider {
		public String updateUserById(User user) {
			return new SQL() {{
					UPDATE("user");
					if(user.getPassword() != null) {
						SET("password = #{password}");
					}
					if(user.getName() != null) {
						SET("name = #{name}");
					}
					if(user.getTelephone() != null) {
						SET("telephone = #{telephone}");
					}
					if(user.getType() != null) {
						SET("type = #{type}");
					}
					WHERE("id = #{id}");
			}}.toString();
		}
	}
}