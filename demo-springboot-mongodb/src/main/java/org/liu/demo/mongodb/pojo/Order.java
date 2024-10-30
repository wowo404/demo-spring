package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * @Author lzs
 * @Date 2023/2/24 11:23
 **/
@Data
public class Order {
    private ObjectId id;
    private Double amount;
    private Date createTime;
    private List<Integer> itemIds;
    private String[] fuckers;//不要这样使用，使用List代替，mongodb的java驱动不支持bson array和java数组的互相转换
}
