package org.liu.demo.mongodb.service;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzs
 * @Date 2023/6/26 16:32
 **/
@SpringBootTest
public class ViewTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void createView(){
        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(Filters.eq("", ""));
        mongoTemplate.getDb().createView("countByAreaNumber", "", pipeline);
    }
}
