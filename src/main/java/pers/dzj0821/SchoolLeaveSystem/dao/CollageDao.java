package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import pers.dzj0821.SchoolLeaveSystem.pojo.Collage;

public interface CollageDao {
	@Select("select * from collage where id = #{id}")
	public Collage selectCollageById(int id) throws Exception;
	
	@Select("select * from collage")
	public List<Collage> selectCollages() throws Exception;
	
	@Insert("insert into collage(name) values(#{name})")
	public int insertCollage(String name) throws Exception;
}
