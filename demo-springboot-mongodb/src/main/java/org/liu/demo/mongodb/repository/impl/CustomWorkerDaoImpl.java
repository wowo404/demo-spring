package org.liu.demo.mongodb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.liu.demo.mongodb.pojo.Worker;
import org.liu.demo.mongodb.repository.CustomWorkerDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class CustomWorkerDaoImpl implements CustomWorkerDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Worker> pageByCustom(String name, PageRequest pageRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name));
        query.skip(pageRequest.getOffset());
        query.limit(pageRequest.getPageSize());
        List<Worker> workers = mongoTemplate.find(query, Worker.class);
        long total = mongoTemplate.count(query, Worker.class);
        return new PageImpl<>(workers, pageRequest, total);
    }
}
