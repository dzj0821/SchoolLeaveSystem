package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Grade;

public interface GradeDao {
	@Select("select * from grade where id = #{id}")
	public Grade selectGradeById(Integer id) throws Exception;
}
