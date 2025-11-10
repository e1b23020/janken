package oit.is.z3069.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {
  @Select("SELECT * FROM matchinfo")
  ArrayList<MatchInfo> selectAllMatchInfos();

  @Insert("INSERT INTO matchinfo ( user1, user2,user1Hand,isActive) VALUES (#{user1}, #{user2},#{user1Hand},#{isActive});")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);

  @Select("SELECT * FROM matchinfo WHERE id = #{id}")
  MatchInfo selectMatchInfoById(int id);
}
