package org.liu.demo.mongodb.service.impl;

import lombok.RequiredArgsConstructor;
import org.liu.demo.mongodb.pojo.Company;
import org.liu.demo.mongodb.repository.CompanyRepository;
import org.liu.demo.mongodb.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * @Author lzs
 * @Date 2022/11/5 9:22
 **/
@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public String add(Company company) {
        companyRepository.save(company);
        return company.getId();
    }
}
