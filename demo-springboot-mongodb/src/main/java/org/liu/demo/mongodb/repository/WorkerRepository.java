package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/8 14:28
 **/
public interface WorkerRepository extends MongoRepository<Worker, String>, CustomWorkerDao {
    //--------------全自动的查询方式，方法名要匹配spring-data的规则-----------------
    List<Worker> findByName(String name);

    //--------------半自动的查询方式，注解有Query，Aggregation，CountQuery，DeleteQuery，ExistsQuery，Update-----------------
    //使用Query注解加上SpEL表达式，实现复杂查询，如果SpEL表达式中的三元运算符返回的不是{key:value}这种形式，则不需要用$and
    @Query("{$and:[" +
            "{name: {$regex: ?0}}," +
            "?#{ [1] == null ? {$expr :true} : { department: [1] } }" +
            "]}")
    Page<Worker> pageByNativeJsonAndSpel(String name, String department, PageRequest pageRequest);

    @Query("{name: {$regex: ?0}}")
    Page<Worker> pageByNativeJson(String name, PageRequest pageRequest);

    //----------自定义的查询方式，要自定义一个Dao接口，让此接口继承它，且要实现自定义接口-----------------
    //在CustomWorkerDao接口中定义方法

    //----------使用Example查询，findAll的多个重载方法-----------------
}
