package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import pers.dzj0821.SchoolLeaveSystem.pojo.Major;

public interface MajorDao {
	@Select("select * from major where id = #{id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "collage_id", property = "collage", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.CollageDao.selectCollageById"))
	})
	public Major selectMajorById(int id) throws Exception;
	
	@Select("select * from major where collage_id = #{id}")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "collage_id", property = "collage", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.CollageDao.selectCollageById"))
	})
	public List<Major> selectMajorsByCollageId(int collageId) throws Exception;
	
	@Select("select * from major")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "collage_id", property = "collage", one = @One(select = "pers.dzj0821.SchoolLeaveSystem.dao.CollageDao.selectCollageById"))
	})
	public List<Major> selectMajors() throws Exception;
	
	@Insert("insert into major(name, collage_id) values(#{name}, #{collage.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertMajor(Major major) throws Exception;
	
	@UpdateProvider(type = MajorDaoProvider.class, method = "updateMajorById")
	public void updateMajorById(Major major) throws Exception;
	
	@Delete("delete from major where id = #{id}")
	public void deleteMajorById(int id) throws Exception;
	
	class MajorDaoProvider {
		public String updateMajorById(Major major) {
			return new SQL() {{
				UPDATE("major");
				if(major.getName() != null) {
					SET("name = #{name}");
				}
				if(major.getCollage() != null && major.getCollage().getId() != null) {
					SET("collage_id = #{collage.id}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
	}
}
