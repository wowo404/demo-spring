package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author lzs
 * @Date 2022/10/28 17:09
 **/
public interface MovieRepository extends MongoRepository<Movie, String> {
}
