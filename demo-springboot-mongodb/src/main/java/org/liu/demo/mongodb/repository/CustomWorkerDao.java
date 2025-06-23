package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomWorkerDao {
    Page<Worker> pageByCustom(String name, PageRequest pageRequest);
}
