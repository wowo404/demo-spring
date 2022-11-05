package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author lzs
 * @Date 2022/10/28 17:09
 **/
public interface CompanyRepository extends MongoRepository<Company, String> {
}
