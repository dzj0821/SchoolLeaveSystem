package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Clazz;

public interface ClazzDao {
	@Select("select * from clazz where id = #{id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "grade_id", property = "grade", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.GradeDao.selectGradeById")),
		@Result(column = "major_id", property = "major", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.MajorDao.selectMajorById"))
	})
	Clazz selectClazzById(Integer id) throws Exception;
}
