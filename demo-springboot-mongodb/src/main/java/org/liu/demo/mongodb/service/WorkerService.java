package org.liu.demo.mongodb.service;

import org.bson.types.ObjectId;
import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.data.domain.Page;

/**
 * @Author lzs
 * @Date 2022/11/8 14:29
 **/
public interface WorkerService {
    ObjectId save(Worker worker);

    Page<Worker> pageByNativeJson(int page, int size, String name);
}