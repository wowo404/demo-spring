package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.CompanyWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyWorkerServiceTest {

    @Autowired
    private CompanyWorkerService companyWorkerService;

    @Test
    void save() {
        //4字节     3字节   2字节  3字节
        //时间戳    机器码  进程ID 自增计数器
        //63687c3c e183ca 4252 e2b337
        CompanyWorker companyWorker = new CompanyWorker();
        //这两个id手动修改过的，只要符合mongo的id格式就可以，要十六进制hexadecimal
        companyWorker.setCompanyId("63687c3ce183ca4252e2b338");
        companyWorker.setWorkerId("6369f83880a6ba54e3e5cafe");
        companyWorkerService.save(companyWorker);
    }
}