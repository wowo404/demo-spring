package org.liu.demo.mongodb.service;

import org.liu.demo.mongodb.pojo.Movie;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/10/28 17:38
 **/
public interface MovieService {
    String batchInsert(Integer size);

    List<Movie> queryAll(String title, Double doubanScore, String firstManActor);

    List<Movie> queryByNaming(String title, Double doubanScore);

    List<Movie> queryByNativeJson(String title, Double doubanScore);
}