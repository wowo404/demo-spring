package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    void batchInsert() {
        for (int i = 0; i < 1000; i++) {
            movieService.batchInsert(50000);
        }
    }

    @Test
    void query() {
        List<Movie> movies = movieService.queryAll("t", 7.0, "a");
        System.out.println(movies);
    }

    @Test
    void queryByNaming() {
        List<Movie> movies = movieService.queryByNaming("t", 7.0);
        System.out.println(movies);
    }

    @Test
    void queryByNativeJson(){
        List<Movie> movies = movieService.queryByNativeJson("am", 6.0);
        System.out.println(movies.size());
        System.out.println(movies);
    }
}