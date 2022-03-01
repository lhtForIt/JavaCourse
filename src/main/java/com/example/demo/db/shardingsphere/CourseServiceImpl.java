package com.example.demo.db.shardingsphere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/28
 */
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getFirstCourse() {
        return courseMapper.getFirstCourse();
    }

    @Override
    public List<Course> getSecondCourse() {
        return courseMapper.getSecondCourse();
    }

    @Override
    public void insertFirstDBUser(Course course) {
        courseMapper.insertFirstDBUser(course);
    }
}
