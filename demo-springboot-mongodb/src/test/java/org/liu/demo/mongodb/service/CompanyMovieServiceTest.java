package org.liu.demo.mongodb.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.CompanyMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Author lzs
 * @Date 2022/11/11 9:55
 **/
@SpringBootTest
public class CompanyMovieServiceTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test(){
        CompanyMovie companyMovie = new CompanyMovie();
        companyMovie.setCompanyId(new ObjectId("63687c3ce183ca4252e2b337"));
        companyMovie.setMovieId(new ObjectId("635f24c5ab5c763fd439dac7"));
        mongoTemplate.save(companyMovie);
    }

}
