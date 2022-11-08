package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkerServiceTest {

    @Autowired
    private WorkerService workerService;

    @Test
    void save() {
        Worker worker = new Worker();
        worker.setName("章遛蠡");
        worker.setCode("1234abcd");
        worker.setPosition("总经理");
        worker.setDepartment("管理办公室");
        worker.setOnboardingTime(new Date());
        workerService.save(worker);
    }
}