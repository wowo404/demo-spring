package org.liu.demo.mongodb.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.liu.demo.mongodb.pojo.Company;
import org.liu.demo.mongodb.util.BeanValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @RepeatedTest(value = 4)
    @Execution(value = ExecutionMode.CONCURRENT)
    @Test
    void testCustomCollectionName() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (int j = 0; j < 10000000; j++) {
            Company company = new Company();
            BeanValueUtils.autoSetValue(company);
            companyService.add(company);
        }
    }

    @Execution(ExecutionMode.CONCURRENT)
    @DisplayName("多个int型入参")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0})
    void intsTest(int candidate) {
        log.info("ints [{}]", candidate);
    }

    @Test
    void testUseMongoTemplate() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Company company = new Company();
        BeanValueUtils.autoSetValue(company);
        company.setPassword(null);
        Company company_202201 = mongoTemplate.insert(company, "company_202202");
        System.out.println(company_202201);
        mongoTemplate.indexOps("company_202202").ensureIndex(new Index("socialUniformCreditCode", Sort.Direction.ASC));
    }

    @Test
    void findById() {
        Company company = companyService.findById("638060ddaad4a27794b4d828");
        System.out.println(company);
    }

    @Test
    void findById2() {
        Company company = mongoTemplate.findById(new ObjectId("63802a0657537174ce2c96ef"), Company.class, "company_202202");
        System.out.println(company);
    }

}