package org.liu.demo.mongodb.service;

import org.liu.demo.mongodb.pojo.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/10/28 17:38
 **/
public interface MovieService {
    String batchInsert(Integer size);

    List<Movie> queryAll(String title, Double doubanScore, String firstManActor);

    Page<Movie> pageByExample(String title, Double doubanScore, String firstManActor, int page, int size);

    List<Movie> queryByNaming(String title, Double doubanScore);

    List<Movie> queryByNativeJson(String title, Double doubanScore);

    Page<Movie> pageList(int page, int size, String title, Double doubanScore);

    void update(Movie movie);

    Movie findById(String id);
}