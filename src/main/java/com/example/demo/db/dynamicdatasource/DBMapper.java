package com.example.demo.db.dynamicdatasource;

import com.example.demo.spring.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
@Mapper
public interface DBMapper {

    @Select("select * from t_user")
    List<User> getFirstDBUser();

    @Select("select * from t_user")
    List<User> getSecondDBUser();

    @Insert("INSERT into t_user (name,age,sex) VALUES ('Leo',18,'男')")
    void insertFirstDBUser();
}
