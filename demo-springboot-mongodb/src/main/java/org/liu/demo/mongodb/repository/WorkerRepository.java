package org.liu.demo.mongodb.repository;

import org.liu.demo.mongodb.pojo.Worker;
import org.liu.demo.mongodb.pojo.req.WorkerReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/8 14:28
 **/
public interface WorkerRepository extends MongoRepository<Worker, String>, CustomWorkerDao {
    //--------------全自动的查询方式，方法名要匹配spring-data的规则-----------------
    //结合@Query注解的fields属性，指定返回字段
    @Query(fields = "{'name': 1,'code': 1}")
    List<Worker> findByName(String name);

    //--------------半自动的查询方式，注解有Query，Aggregation，CountQuery，DeleteQuery，ExistsQuery，Update-----------------
    //使用Query注解加上SpEL表达式，实现复杂查询，如果SpEL表达式中的三元运算符返回的不是{key:value}这种形式，则不需要用$and
    @Query("{$and:[" +
            "{name: {$regex: ?0}}," +
            "?#{ [1] == null ? {$expr :true} : { department: [1] } }" +
            "]}")
    Page<Worker> pageByNativeJsonAndSpel(String name, String department, PageRequest pageRequest);

    //Query注解中获取对象中的字段
    @Query("{$and:[" +
            "{name: {$regex: ?#{[0].name}}}," +
            "?#{ [0].department == null ? {$expr :true} : { department: [0].department } }" +
            "]}")
    Page<Worker> pageByNativeJsonAndSpel2(WorkerReq req, PageRequest pageRequest);

    @Query("{name: {$regex: ?0}}")
    Page<Worker> pageByNativeJson(String name, PageRequest pageRequest);

    //---------spel方式取值并判断，idea提示错误，实际是没问题--------------
    @Query(value = "{ 'code': ?0 }," +
            "$expr: ?#{ " +
            "   { $and: [" +
            "       ?#{#createTimeAfter != null ? { $gte: ['$updateTime', #createTimeAfter] } : true}," +
            "       ?#{#createTimeBefore != null ? { $lte: ['$updateTime', #createTimeBefore] } : true}" +
            "   ]}" +
            "}")
    Page<Worker> findRecords(
            String code, @Nullable Date createTimeAfter, @Nullable Date createTimeBefore, PageRequest pageRequest);

    //----------自定义的查询方式，要自定义一个Dao接口，让此接口继承它，且要实现自定义接口-----------------
    //在CustomWorkerDao接口中定义方法

    //----------使用Example查询，findAll的多个重载方法-----------------

    @Query("{code: ?1}")
    @Update("{$set: {name: ?0}}")
    void updateCustom(String name, String code);
}
