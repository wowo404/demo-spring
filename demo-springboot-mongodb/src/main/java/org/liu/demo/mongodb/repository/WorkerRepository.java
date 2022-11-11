package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @Author lzs
 * @Date 2022/11/8 14:28
 **/
public interface WorkerRepository extends MongoRepository<Worker, String> {
    @Query("{name: {$regex: ?0}}")
    Page<Worker> pageByNativeJson(String name, PageRequest pageRequest);
}
