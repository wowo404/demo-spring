package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Company;
import org.liu.demo.mongodb.util.BeanValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    void testCustomCollectionName() throws InvocationTargetException, IllegalAccessException {
        Company company = new Company();
        BeanValueUtils.autoSetValue(company);
        companyService.add(company);
    }

}