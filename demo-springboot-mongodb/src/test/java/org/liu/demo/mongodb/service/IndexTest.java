package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    public void test(){
        Worker worker = new Worker();
        worker.setCode("test1987");
        worker.setName("Jackson Wood");
        worker.setPosition("product manager");
        worker.setDepartment("product");
        mongoTemplate.save(worker, "worker_1988");
        mongoTemplate.indexOps("worker_1988").ensureIndex(new Index("age", Sort.Direction.DESC));
    }

}
