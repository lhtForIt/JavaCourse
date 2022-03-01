package com.example.demo.db.shardingsphere;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/28
 */
@Mapper
public interface CourseMapper {

    @Select("select * from course where cid in (1,2)")
    List<Course> getFirstCourse();

    @Select("select * from course where cid in (1,2)")
    List<Course> getSecondCourse();

    @Insert("insert into course (cid,cname,user_id,cstatus) values (#{course.cid},#{course.cname},#{course.userId},#{course.cstatus})")
    void insertFirstDBUser(@Param("course") Course course);

}
