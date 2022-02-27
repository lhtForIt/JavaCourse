package com.example.demo.db.dynamicdatasource;

import com.example.demo.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
@Service
public class DBServiceImpl implements DBService{

    @Autowired
    private DBMapper mapper;

    @Override
    public List<User> getFirstDBUser() {
        return mapper.getFirstDBUser();
    }

    @CurDataSource(name = "second")
    @Override
    public List<User> getSecondDBUser() {
        return mapper.getSecondDBUser();
    }
}
