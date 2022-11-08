package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.CompanyWorker;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author lzs
 * @Date 2022/11/8 14:22
 **/
public interface CompanyWorkerRepository extends MongoRepository<CompanyWorker, String> {
}