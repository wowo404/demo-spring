package org.liu.demo.mongodb.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Company;
import org.liu.demo.mongodb.util.BeanValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testCustomCollectionName() throws InvocationTargetException, IllegalAccessException {
        Company company = new Company();
        BeanValueUtils.autoSetValue(company);
        companyService.add(company);
    }

    @Test
    void testUseMongoTemplate() throws InvocationTargetException, IllegalAccessException {
        Company company = new Company();
        BeanValueUtils.autoSetValue(company);
        company.setPassword(null);
        Company company_202201 = mongoTemplate.insert(company, "company_202202");
        System.out.println(company_202201);
        mongoTemplate.indexOps("company_202202").ensureIndex(new Index("socialUniformCreditCode", Sort.Direction.ASC));
    }

    @Test
    void findById(){
        Company company = companyService.findById("638060ddaad4a27794b4d828");
        System.out.println(company);
    }

    @Test
    void findById2(){
        Company company = mongoTemplate.findById(new ObjectId("63802a0657537174ce2c96ef"), Company.class, "company_202202");
        System.out.println(company);
    }

}