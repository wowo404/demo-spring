package org.liu.demo.mongodb.service;

import org.liu.demo.mongodb.pojo.Company;

/**
 * @Author lzs
 * @Date 2022/11/5 9:22
 **/
public interface CompanyService {
    String add(Company company);

    Company findById(String id);
}