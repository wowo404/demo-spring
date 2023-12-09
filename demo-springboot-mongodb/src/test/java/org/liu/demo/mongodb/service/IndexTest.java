package org.liu.demo.mongodb.service;

import com.mongodb.client.ListIndexesIterable;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.HashedIndex;
import org.springframework.data.mongodb.core.index.Index;

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
        worker.setCode("test1987");
        worker.setName("Jackson Wood");
        worker.setPosition("product manager");
        worker.setDepartment("product");
        mongoTemplate.save(worker, "worker_1988");
        mongoTemplate.indexOps("worker_1988").ensureIndex(new Index("age", Sort.Direction.DESC));
        mongoTemplate.indexOps("worker_1988").ensureIndex(HashedIndex.hashed("code"));
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
