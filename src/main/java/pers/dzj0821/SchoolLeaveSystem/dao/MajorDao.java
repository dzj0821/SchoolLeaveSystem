package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
}
