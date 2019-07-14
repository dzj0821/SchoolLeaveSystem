package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Grade;

public interface GradeDao {
	@Select("select * from grade where id = #{id}")
	public Grade selectGradeById(int id) throws Exception;
	
	@Select("select * from grade")
	public List<Grade> selectGrades() throws Exception;
	
	@Insert("insert into grade(year) values(#{grade})")
	public int insertGrade(int grade) throws Exception;
	
}
