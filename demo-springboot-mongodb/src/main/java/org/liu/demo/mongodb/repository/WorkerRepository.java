package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author lzs
 * @Date 2022/11/8 14:28
 **/
public interface WorkerRepository extends MongoRepository<Worker, String> {
}
