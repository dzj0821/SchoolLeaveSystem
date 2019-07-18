package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import pers.dzj0821.SchoolLeaveSystem.pojo.Clazz;

public interface ClazzDao {
	@Select("select * from clazz where id = #{id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "grade_id", property = "grade", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.GradeDao.selectGradeById")),
		@Result(column = "major_id", property = "major", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.MajorDao.selectMajorById"))
	})
	Clazz selectClazzById(int id) throws Exception;
	
	@Select("select * from clazz where major_id = #{majorId}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "grade_id", property = "grade", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.GradeDao.selectGradeById")),
		@Result(column = "major_id", property = "major", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.MajorDao.selectMajorById"))
	})
	List<Clazz> selectClazzesByMajorId(int majorId) throws Exception;
	
	@SelectProvider(type = ClazzDaoProvider.class)
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "grade_id", property = "grade", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.GradeDao.selectGradeById")),
		@Result(column = "major_id", property = "major", one=@One(select = "pers.dzj0821.SchoolLeaveSystem.dao.MajorDao.selectMajorById"))
	})
	List<Clazz> selectClazzesByClazz(Clazz clazz) throws Exception;
	
	@Insert("insert into clazz(no, grade_id, major_id) values(#{no}, #{grade.id}, #{major.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertClazz(Clazz clazz) throws Exception;
	
	@UpdateProvider(type = ClazzDaoProvider.class)
	int updateClazzById(Clazz clazz) throws Exception;
	
	@Delete("delete from clazz where id = #{id}")
	int deleteClazzById(Integer id) throws Exception;
	
	class ClazzDaoProvider implements ProviderMethodResolver {
		public String selectClazzesByClazz(Clazz clazz) {
			return new SQL() {{
				SELECT("*");
				FROM("clazz");
				if(clazz.getId() != null) {
					WHERE("id = #{id}");
				}
				if(clazz.getNo() != null) {
					WHERE("no = #{no}");
				}
				if(clazz.getGrade() != null && clazz.getGrade().getId() != null) {
					WHERE("grade_id = #{grade.id}");
				}
				if(clazz.getMajor() != null && clazz.getMajor().getId() != null) {
					WHERE("major_id = #{major.id}");
				}
			}}.toString();
		}
		
		public String updateClazzById(Clazz clazz) {
			return new SQL() {{
				UPDATE("clazz");
				if(clazz.getNo() != null) {
					SET("no = #{no}");
				}
				if(clazz.getGrade() != null && clazz.getGrade().getId() != null) {
					SET("grade_id = #{grade.id}");
				}
				if(clazz.getMajor() != null && clazz.getMajor().getId() != null) {
					SET("major_id = #{major.id}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
	}
}
