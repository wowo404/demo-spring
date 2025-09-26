package org.liu.demo.mongodb.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.enums.SexEnum;
import org.liu.demo.mongodb.pojo.Order;
import org.liu.demo.mongodb.util.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        order.setAmount(123.22);
        order.setCreateTime(new Date());
        order.setItemIds(Arrays.asList(1, 2, 3));
        order.setFuckers(new String[]{"good", "bad"});
        order.setSex(SexEnum.FEMALE);//枚举字段，存入数据库的值是 FEMALE
//        mongoTemplate.insert(order);
        //换成save方法则成功，save方法内部会判断id是否存在
        mongoTemplate.save(order, "test_order");
    }

    @Test
    void query() {
        //sex字段为枚举，可以自动转换
        Order order = mongoTemplate.findById("63941a6c5a3f6e3490af0bfa", Order.class, "test_order");
        System.out.println(order);
    }

    @Test
    void insertOne() {
        List<Integer> itemIds = new ArrayList<>();
        itemIds.add(6);
        itemIds.add(5);
        itemIds.add(4);
        Document document = new Document();
        document.put("amount", 33.44);
        document.put("createTime", new Date());
        document.put("itemIds", itemIds);
        document.put("fuckers", new String[]{"josh", "clack"});
        mongoTemplate.getCollection("test_order").withCodecRegistry(MongoUtils.getCodecRegistry()).insertOne(document);
    }

    /**
     * 读取bson array转换为java array
     * 只是为了测试和深入了解api
     */
    @Test
    void readBsonArrayToJavaArray() {
        FindIterable<Order> iterable = mongoTemplate.getCollection("test_order").withCodecRegistry(MongoUtils.getCodecRegistry()).find(Order.class);
        for (Order order : iterable) {
            System.out.println(order);
        }
    }

    //使用原生api根据_id删除，要用new ObjectId()
    @Test
    void delete() {
        DeleteResult deleteResult = mongoTemplate.getCollection("orders").deleteOne(Filters.eq("_id", new ObjectId("63940a6c5a3f6e3490af0bfa")));
        System.out.println(deleteResult);
    }

}
