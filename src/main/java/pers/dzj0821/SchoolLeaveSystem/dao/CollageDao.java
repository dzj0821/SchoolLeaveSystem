package pers.dzj0821.SchoolLeaveSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pers.dzj0821.SchoolLeaveSystem.pojo.Collage;

public interface CollageDao {
	@Select("select * from collage where id = #{id}")
	public Collage selectCollageById(int id) throws Exception;
	
	@Select("select * from collage")
	public List<Collage> selectCollages() throws Exception;
	
	@Insert("insert into collage(name) values(#{name})")
	public int insertCollage(String name) throws Exception;
	
	@Update("update collage set name = #{name} where id = #{id}")
	public void updateCollageById(Collage collage) throws Exception;
	
	@Delete("delete from collage where id = #{id}")
	public void deleteCollageById(int id) throws Exception;
}
