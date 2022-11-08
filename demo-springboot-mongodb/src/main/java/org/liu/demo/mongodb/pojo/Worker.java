package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @Author lzs
 * @Date 2022/11/8 14:17
 **/
@Data
public class Worker {
    @MongoId
    private String id;
    private String name;
    private String code;
    private String position;
    private String department;
    private Date onboardingTime;
}
