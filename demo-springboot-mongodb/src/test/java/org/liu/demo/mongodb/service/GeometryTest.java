package org.liu.demo.mongodb.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.TestGeometry;
import org.liu.demo.mongodb.util.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lzs
 * @Date 2024/8/16 14:19
 **/
@SpringBootTest
public class GeometryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
//        String json = "{'type': 'Feature', 'geometry': {'type': 'Point', 'coordinates': [116.43, 39.86] }, 'properties': {'name': 'BeiJing'}}";
        String json = "{'type': 'Feature', 'geometry': {'type': 'Polygon', 'coordinates': [[[120.60,37.38],[119.98,36.65],[121.58,37.00],[120.60,37.38]]] }, 'properties': {'name': 'ShanDong'}}";
        MongoCollection<Document> collection = mongoTemplate.getCollection("test_geometry");
        InsertOneResult result = collection.insertOne(Document.parse(json));
        System.out.println(result.wasAcknowledged() + "，" + result.getInsertedId());
        collection.createIndex(Indexes.geo2dsphere("geometry"));
    }

    //注意：codecRegistry要注册TestGeometry类或TestGeometry所在的包
    @Test
    public void query() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("test_geometry").withCodecRegistry(MongoUtils.getCodecRegistry());
        FindIterable<TestGeometry> iterable = collection.find(Filters.empty(), TestGeometry.class);
        for (TestGeometry testGeometry : iterable) {
            System.out.println(testGeometry);
        }
    }

}
