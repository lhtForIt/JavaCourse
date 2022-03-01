package com.example.demo.db.shardingsphere;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/28
 */
public interface CourseService {

    List<Course> getFirstCourse();

    List<Course> getSecondCourse();

    void insertFirstDBUser(Course course);

}
