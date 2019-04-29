package pers.dzj0821.SchoolLeaveSystem.dao;

import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Collage;

public interface CollageDao {
	@Select("select * from collage where id = #{id}")
	public Collage findCollageById(int id) throws Exception;
}
