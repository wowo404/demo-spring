package org.liu.demo.mongodb.service;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.*;

/**
 * @Author lzs
 * @Date 2023/2/13 11:24
 **/
@SpringBootTest
public class IndexTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        Worker worker = new Worker();
        worker.setCode("3test1981");
        worker.setName("Women Fucker");
        worker.setPosition("programmer");
        worker.setDepartment("development");
        worker.setPoint(new Point(new Position(33.2245, 25.4478)));
        Worker result = mongoTemplate.save(worker, "worker_202211");
        System.out.println("结果：" + result);
        IndexOperations indexOps = mongoTemplate.indexOps("worker_202211");
        indexOps.ensureIndex(new Index("age", Sort.Direction.DESC));
        indexOps.ensureIndex(HashedIndex.hashed("code"));
        indexOps.ensureIndex(new GeospatialIndex("point").typed(GeoSpatialIndexType.GEO_2DSPHERE));
    }

    @Test
    public void listIndex() {
        ListIndexesIterable<Document> indexesIterable = mongoTemplate.getCollection("worker_1988").listIndexes();
        for (Document document : indexesIterable) {
            BsonDocument indexPair = document.toBsonDocument().getDocument("key");
            String key = indexPair.getFirstKey();
            BsonValue value = indexPair.get(key);
            System.out.println(key + " -- " + value);
        }
    }

}
