package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @Author lzs
 * @Date 2023/2/24 11:23
 **/
@Data
public class Order {
    private ObjectId id;
    private Double amount;
    private Date createTime;
}
