package org.liu.demo.mongodb.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author lzs
 * @Date 2022/11/8 14:17
 **/
@Data
public class Worker {
    private String id;
    private String name;
    private String code;
    private String position;
    private String department;
    private Date onboardingTime;
}
