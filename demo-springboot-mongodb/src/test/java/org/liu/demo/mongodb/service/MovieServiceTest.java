package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}