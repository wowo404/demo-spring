package org.liu.demo.mongodb.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.liu.demo.mongodb.pojo.Worker;
import org.liu.demo.mongodb.pojo.req.WorkerReq;
import org.liu.demo.mongodb.repository.WorkerRepository;
import org.liu.demo.mongodb.service.WorkerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/8 14:29
 **/
@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Override
    public ObjectId save(Worker worker) {
        workerRepository.save(worker);
        return worker.getId();
    }

    @Override
    public List<Worker> findByName(String name) {
        return workerRepository.findByName(name);
    }

    @Override
    public Page<Worker> pageByNativeJson(int page, int size, String name) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return workerRepository.pageByNativeJson(name, pageRequest);
    }

    @Override
    public Page<Worker> pageByNativeJsonAndSpel(int page, int size, String name, String department) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return workerRepository.pageByNativeJsonAndSpel(name, department, pageRequest);
    }

    @Override
    public Page<Worker> pageByNativeJsonAndSpel2(int page, int size, WorkerReq req) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return workerRepository.pageByNativeJsonAndSpel2(req, pageRequest);
    }

    @Override
    public Page<Worker> pageByCustom(int page, int size, String name) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return workerRepository.pageByCustom(name, pageRequest);
    }

    @Override
    public Page<Worker> pageByExample(int page, int size, String name) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Worker worker = new Worker();
        worker.setId(new ObjectId(new Date()));
        worker.setName(name);
        Example<Worker> example = Example.of(worker, ExampleMatcher.matching()
                .withIgnoreNullValues()//忽略值为null的属性
                .withIgnorePaths("id"));//忽略id属性
        return workerRepository.findAll(example, pageRequest);
    }
}
