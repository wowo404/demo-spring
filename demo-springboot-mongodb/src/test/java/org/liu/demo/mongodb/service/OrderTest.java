package org.liu.demo.mongodb.service;

import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

/**
 * @Author lzs
 * @Date 2023/2/24 11:24
 **/
@SpringBootTest
public class OrderTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testUpdate() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in("63940a6c5a3f6e3490af0bfa"));
        Update update = Update.update("itemCount", 10);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "orders");
        System.out.println(updateResult);
    }

    //insert方法，如果id在数据不存在，则成功，存在则失败
    @Test
    void insert() {
        Order order = new Order();
        order.setId(new ObjectId("63941a6c5a3f6e3490af0bfa"));
        order.setAmount(100.22);
        order.setCreateTime(new Date());
        mongoTemplate.insert(order);
        //换成save方法则成功，save方法内部会判断id是否存在
        mongoTemplate.save(order);
    }

    //使用原生api根据_id删除，要用new ObjectId()
    @Test
    void delete() {
        DeleteResult deleteResult = mongoTemplate.getCollection("orders").deleteOne(Filters.eq("_id", new ObjectId("63940a6c5a3f6e3490af0bfa")));
        System.out.println(deleteResult);
    }

}
