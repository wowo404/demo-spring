package org.liu.demo.mongodb.service;

import cn.hutool.core.date.DateUtil;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class WorkerServiceTest {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void save() {
        Worker worker = new Worker();
        worker.setName("章遛蠡");
        worker.setCode("1234abcd");
        worker.setPosition("总经理");
        worker.setDepartment("管理办公室");
        worker.setOnboardingTime(new Date());
        workerService.save(worker);
    }

    @Test
    void pageUseRepository() {
        int page = 1;
        int size = 10;
        String name = "a";
        Page<Worker> page1 = workerService.pageByNativeJson(page, size, name);
        System.out.println(page1.getTotalElements());
        System.out.println(page1.getContent());
    }

    @Test
    void saveWithTpl() {
        Worker worker = new Worker();
        worker.setId(new ObjectId("636b1784012c816410bbb908"));
        worker.setName("myForest");
        worker.setCode("text1234");
        worker.setPosition("人体内");
        worker.setDepartment("保护让他人局");
        worker.setOnboardingTime(DateUtil.offsetDay(new Date(), -32));
        mongoTemplate.insert(worker, "work_202211");
    }

    @Test
    void page() {
        Query query = new Query();
        Criteria andCriteria1 = Criteria.where("code").is("1qaz");
        Criteria orCriteria = new Criteria();
        orCriteria.orOperator(Criteria.where("position").is("老鸨"), Criteria.where("position").isNull());
        Criteria criteria = new Criteria();
        criteria.andOperator(andCriteria1, orCriteria);
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, Worker.class, "work_202211");
        long totalPage = count / 10 + 1;
        for (int i = 0; i < totalPage; i++) {
            query.skip(i * 10);
            query.limit(10);
            List<Worker> list = mongoTemplate.query(Worker.class).inCollection("work_202211").matching(query).all();
            System.out.println(list);
        }
    }

    @Test
    void findAll() {
        List<Worker> list = mongoTemplate.findAll(Worker.class, "work_202211");
        System.out.println(list);
    }

    @Test
    void findById() {
        Worker two = mongoTemplate.findById("636b0bc346db0842539ce7b3", Worker.class, "work_202211");
        System.out.println(two);
    }

    @Test
    void findOne() {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").in(Collections.singletonList("636b0bc346db0842539ce7b3"));
        query.addCriteria(criteria);

        List<Worker> one = mongoTemplate.find(query, Worker.class, "work_202211");
        System.out.println(one);
    }

    @Test
    void findWithChainCode() {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is("636b0bc346db0842539ce7b3");
        query.addCriteria(criteria);
        Worker worker = mongoTemplate.query(Worker.class).inCollection("work_202211").matching(query).firstValue();
        System.out.println(worker);
    }

    /**
     * 注意：此方法跟下面的update方法的区别
     */
    @Test
    void updateWithObjectId() {
        Query query = new Query();
        //此处条件要用_id
        Criteria criteria = Criteria.where("_id").is(new ObjectId("636b1784012c816410bbb908"));
        query.addCriteria(criteria);

        Update update = new Update();
        update.set("name", "liuzhangsheng");
        update.set("code", "1111");

        //此处没有加Worker.class这个参数
        long count = mongoTemplate.updateFirst(query, update, "work_202211").getModifiedCount();
        System.out.println(count);
    }

    @Test
    void update() {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is("636b1784012c816410bbb908");
        query.addCriteria(criteria);

        Update update = new Update();
        update.set("name", "abcd1234");
        update.unset("code");
        //第三个参数可有可无
        long count = mongoTemplate.updateFirst(query, update, Worker.class, "work_202211").getModifiedCount();
        System.out.println(count);
    }

    /**
     * 此方法会将id=636b0bc346db0842539ce7b3的记录更新为只有name和code两个字段，其他字段丢弃
     */
    @Test
    void updateWithObject() {
        Worker worker = new Worker();
        worker.setName("John");
        worker.setCode("ok");

        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is("636b0bc346db0842539ce7b3");
        query.addCriteria(criteria);

        Worker replaceValue = mongoTemplate.update(Worker.class).inCollection("work_202211").matching(query).replaceWith(worker).findAndReplaceValue();
        System.out.println(replaceValue);
    }

    @Test
    void updateWithObject2() {
        Worker worker = new Worker();
        worker.setId(new ObjectId("636b0bc346db0842539ce7b3"));
        worker.setName("John");
        worker.setCode("ok");
        worker.setPosition("manager");

        Worker replaceValue = mongoTemplate.update(Worker.class).inCollection("work_202211").replaceWith(worker).findAndReplaceValue();
        System.out.println(replaceValue);
    }

    //将POJO转成Document，不指定Class，更新数据
    @Test
    void updateWithObject3() {
        Worker worker = new Worker();
        worker.setId(new ObjectId("636b0bc346db0842539ce7b3"));
        worker.setName("Sunny");
        worker.setCode("123");
        worker.setPosition("programmer");
        worker.setDepartment("development");

        Document document = new Document();
        mongoTemplate.getConverter().write(worker, document);
        Update update = Update.fromDocument(document);

        Query query = new Query(Criteria.where("_id").is("636b0bc346db0842539ce7b3"));

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "work_202211");
        System.out.println(updateResult);
    }

    @Test
    void updateWithMongoApi() {
        Worker worker = new Worker();
//        worker.setId(new ObjectId("636b0bc346db0842539ce7b3"));
        worker.setName("James");
        worker.setCode("a");
        worker.setPosition("Test Engineer");
        worker.setDepartment("Test Department");

        Document document = new Document();
        mongoTemplate.getConverter().write(worker, document);

        Bson filters = Filters.eq("_id", new ObjectId("636b0bc346db0842539ce7b3"));
        List<Bson> updates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            updates.add(Updates.set(entry.getKey(), entry.getValue()));
        }
        UpdateResult updateResult = mongoTemplate.getCollection("work_202211").updateOne(filters, Updates.combine(updates));
        System.out.println(updateResult);
    }

    @Test
    void updateWithBson() {
        Date now = new Date();
        DateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        String dateTime = format.format(now);
        String dateTime1 = DateUtil.formatDate(now) + "T" + DateUtil.formatTime(now) + "Z";
        String filter = "{_id:ObjectId('636b0bc346db0842539ce7b3')}";
        Document filterDoc = Document.parse(filter);
        String set = "{$set:{updateTime:new Date('" + dateTime + "'),onboardingTime:new ISODate('" + dateTime1 + "')},$inc:{version: 1}}";
        Document setDoc = Document.parse(set);
        UpdateResult updateResult = mongoTemplate.getCollection("work_202211").updateOne(filterDoc, setDoc);
        System.out.println(updateResult);
    }

}