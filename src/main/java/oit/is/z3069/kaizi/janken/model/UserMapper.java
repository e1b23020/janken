package oit.is.z3069.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users")
  ArrayList<User> selectALLUser();

  @Select("SELECT * FROM users WHERE id = #{id}")
  User selectUserById(int id);

  @Select("SELECT * FROM users WHERE userName = #{userName}")
  User selectUserByName(String userName);
}
