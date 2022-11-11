package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/10/28 17:09
 **/
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTitleContainingAndDoubanScoreGreaterThanEqual(String title, Double doubanScore, Sort sort);

    @Query("{title: {$regex: ?0}, doubanScore: {$gt: ?1}}")
    List<Movie> findByNativeJson(String title, Double doubanScore);

    @Query("{title: {$regex: ?0}, doubanScore: {$gt: ?1}}")
    Page<Movie> pageByNativeJson(String title, Double doubanScore, PageRequest pageRequest);
}
