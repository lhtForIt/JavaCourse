package com.example.demo.db.shardingsphere;

import lombok.Data;

/**
 * @author Leo liang
 * @Date 2022/2/27
 */
@Data
public class Course {

    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
