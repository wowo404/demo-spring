package org.liu.demo.mongodb.service;

import org.bson.types.ObjectId;
import org.liu.demo.mongodb.pojo.Worker;
import org.liu.demo.mongodb.pojo.req.WorkerReq;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/8 14:29
 **/
public interface WorkerService {
    ObjectId save(Worker worker);

    List<Worker> findByName(String name);

    Page<Worker> pageByNativeJson(int page, int size, String name);

    Page<Worker> pageByNativeJsonAndSpel(int page, int size, String name, String department);

    Page<Worker> pageByNativeJsonAndSpel2(int page, int size, WorkerReq req);

    Page<Worker> pageByCustom(int page, int size, String name);

    Page<Worker> pageByExample(int page, int size, String name);
}