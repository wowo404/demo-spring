package org.liu.demo.mongodb.service.impl;

import lombok.RequiredArgsConstructor;
import org.liu.demo.mongodb.pojo.CompanyWorker;
import org.liu.demo.mongodb.repository.CompanyWorkerRepository;
import org.liu.demo.mongodb.service.CompanyWorkerService;
import org.springframework.stereotype.Service;

/**
 * @Author lzs
 * @Date 2022/11/8 14:23
 **/
@RequiredArgsConstructor
@Service
public class CompanyWorkerServiceImpl implements CompanyWorkerService {

    private final CompanyWorkerRepository companyWorkerRepository;

    @Override
    public String save(CompanyWorker companyWorker) {
        companyWorkerRepository.save(companyWorker);
        return companyWorker.getId();
    }
}
