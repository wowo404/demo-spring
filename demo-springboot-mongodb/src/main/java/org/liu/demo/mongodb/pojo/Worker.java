package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * @Author lzs
 * @Date 2022/11/8 14:17
 **/
@Data
public class Worker {
    private ObjectId id;
    private String name;
    @Indexed
    private String code;
    private String position;
    private String department;
    private Date onboardingTime;
    private Date updateTime;
}
