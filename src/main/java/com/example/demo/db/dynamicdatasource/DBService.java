package com.example.demo.db.dynamicdatasource;

import com.example.demo.spring.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
public interface DBService {

    List<User> getFirstDBUser();

    void insertFirstDBUser();

    List<User> getSecondDBUser();

}
