package org.liu.demo.reactive.repository.mongodb;

import org.liu.demo.reactive.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @Author lzs
 * @Date 2023/2/14 14:12
 **/
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}