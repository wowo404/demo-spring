package org.liu.demo.mongodb.service.impl;

import lombok.RequiredArgsConstructor;
import org.liu.demo.mongodb.pojo.Worker;
import org.liu.demo.mongodb.repository.WorkerRepository;
import org.liu.demo.mongodb.service.WorkerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author lzs
 * @Date 2022/11/8 14:29
 **/
@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Override
    public String save(Worker worker) {
        workerRepository.save(worker);
        return worker.getId();
    }

    @Override
    public Page<Worker> pageByNativeJson(int page, int size, String name) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return workerRepository.pageByNativeJson(name, pageRequest);
    }
}
