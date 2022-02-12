package com.example.demo.spring.jdbc.mapper;

import com.example.demo.spring.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/13
 */
@Repository
public interface myMapper {

    @Select("SELECT * FROM user")
    List<User> getUser(int age);

    @Update("UPDATE user SET name=#{user.name} WHERE age = #{user1.age}")
    void updateUser(@Param("user") User user, @Param("user1") User user1);

    @Insert("INSERT INTO user ('name','sex','age') VALUES(#{user.name},#{user.sex},#{user.age})")
    void addUser(User user);

    @Delete("DELETE FROM user WHERE age = #{user.age}")
    void deleteUser(User user);


}
