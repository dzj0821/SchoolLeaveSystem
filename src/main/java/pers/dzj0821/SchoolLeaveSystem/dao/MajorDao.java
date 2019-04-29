package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Major;

public interface MajorDao {
	@Select("select * from major where id = #{id}")
	@Results({
		@Result(column="collage_id", property="collage",one=@One(select="pers.dzj0821.SchoolLeaveSystem.dao.CollageDao.findCollageById"))
	})
	public Major findMajorById(int id) throws Exception;
}
