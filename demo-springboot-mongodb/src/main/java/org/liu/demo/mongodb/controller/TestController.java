package org.liu.demo.mongodb.controller;

import com.mongodb.ConnectionString;
import com.mongodb.DBObjectCodecProvider;
import com.mongodb.DBRefCodecProvider;
import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.client.*;
import com.mongodb.client.gridfs.codecs.GridFSFileCodecProvider;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bson.codecs.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.liu.demo.mongodb.constant.Constants;
import org.liu.demo.mongodb.pojo.PointValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("testMongoClient")
    public List<Document> testMongoClient() {
        String db = "logistics_test";
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@192.168.17.230:27017/logistics_test");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoCollection<Document> collection = mongoClient.getDatabase(db).getCollection("point_value_" + 50);
        Bson startTime = Filters.gte("time", 1569426205667L);
        Bson endTime = Filters.lte("time", 1569427168202L);
        FindIterable<Document> documents = collection.find(Filters.and(startTime, endTime));
        MongoCursor<Document> iterator = documents.iterator();
        List<Document> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Document next = iterator.next();
            list.add(next);
        }
        return list;
    }

    @GetMapping("testBigDecimal")
    public String testBigDecimal(@RequestParam Long time) {
        PointValueDTO dto = new PointValueDTO();
        dto.setTime(time);
        dto.setCreate_time(new Date());
        dto.setData_source(1);
        dto.setValue(new BigDecimal("123456.333"));
        dto.setSwitcher(true);
        mongoTemplate.getDb().getCollection("my-test-hello", PointValueDTO.class).withCodecRegistry(pojoCodecRegistry).insertOne(dto);
        return "success";
    }

    @GetMapping("queryForecast")
    public List<PointValueDTO> queryForecast(@RequestParam("evId") String evId,
                                             @RequestParam(value = "beginTime", required = false) Long btime,
                                             @RequestParam(value = "endTime", required = false) Long etime,
                                             @RequestParam(value = "pointVarId", required = false) Long pointVarId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.where("ev_id").is(evId);
        if (Objects.nonNull(btime))
            criteria.and("time").gte(btime);
        if (Objects.nonNull(etime))
            criteria.and("time").lte(etime);
        if (Objects.nonNull(pointVarId))
            criteria.and("value_id").is(pointVarId);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, PointValueDTO.class, "forecast_value_" + evId);
    }

    @GetMapping("testOriginalQuery")
    public List<PointValueDTO> testOriginalQuery() {
        //用spring封装的find
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("time").is(1565747076063L);
        query.addCriteria(criteria);
        PointValueDTO dto = mongoTemplate.findOne(query, PointValueDTO.class, "point_value_6483");
        System.out.println(dto);

        //用mongo-java的原生api，不转换对象，用Document装载数据
        AggregateIterable<Document> aggregate = mongoTemplate.getDb().getCollection("point_value_6483").aggregate(Arrays.asList(Aggregates.sample(1)));
        MongoCursor<Document> mongoCursor = aggregate.iterator();
        while (mongoCursor.hasNext()) {
            Document next = mongoCursor.next();
            System.out.println(next.toJson());
        }
        //用spring封装的api
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(1));
        AggregationResults<PointValueDTO> aggregationResults = mongoTemplate.aggregate(aggregation, "point_value_6483", PointValueDTO.class);
        List<PointValueDTO> mappedResults = aggregationResults.getMappedResults();
        for (PointValueDTO mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

        //用mongo-java的原生api
        AggregateIterable<PointValueDTO> agg = mongoTemplate.getCollection("point_value_6483")
                .withCodecRegistry(pojoCodecRegistry).aggregate(Arrays.asList(Aggregates.sample(1)), PointValueDTO.class);
        MongoCursor<PointValueDTO> iterator = agg.iterator();
        List<PointValueDTO> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @GetMapping("createIndex")
    public String createIndex() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for (String collectionName : collectionNames) {
            if (collectionName.startsWith(Constants.H_FIVE_MIN) || collectionName.startsWith(Constants.H_FIFTEEN_MIN)) {
                IndexOperations indexOperations = mongoTemplate.indexOps(collectionName);
                indexOperations.ensureIndex(new Index().on("time", Sort.Direction.DESC));
                indexOperations.ensureIndex(new Index().on("value_id", Sort.Direction.ASC));
            } else if (collectionName.startsWith("trend_data_")) {
                IndexOperations indexOperations = mongoTemplate.indexOps(collectionName);
                indexOperations.ensureIndex(new Index().on("ev_id", Sort.Direction.ASC));
                indexOperations.ensureIndex(new Index().on("time", Sort.Direction.DESC));
                indexOperations.ensureIndex(new Index().on("value_id", Sort.Direction.ASC));
            } else if (collectionName.startsWith(Constants.POINT_VALUE_PREFIX)) {
                IndexOperations indexOperations = mongoTemplate.indexOps(collectionName);
                indexOperations.ensureIndex(new Index().on("value_id", Sort.Direction.ASC));
            }
        }
        return "success";
    }

    /**
     * 多加了个index，去除它
     */
    @GetMapping("removeIndex")
    public String removeIndex(String indexName) {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for (String collectionName : collectionNames) {
            if (!collectionName.startsWith(Constants.POINT_VALUE_PREFIX)) {
                continue;
            }
            IndexOperations indexOperations = mongoTemplate.indexOps(collectionName);
            List<IndexInfo> indexInfoList = indexOperations.getIndexInfo();
            boolean match = indexInfoList.stream().anyMatch(indexInfo -> indexName.equals(indexInfo.getName()));
            if (match) {
                indexOperations.dropIndex(indexName);
            }
        }
        return "success";
    }

    /**
     * 检查重复数据
     * db.getCollection('point_value_1').aggregate([{ $group: { _id : '$time', count: { $sum : 1 } } },{ $match: { count: { $gt : 1} } }])
     * 重复数据只留一条
     */
    @GetMapping("checkDuplicateData")
    public void checkDuplicateData() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for (String collectionName : collectionNames) {
            if (!collectionName.startsWith(Constants.POINT_VALUE_PREFIX)) {
                continue;
            }
            MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
            List<Bson> bsons = Arrays.asList(
                    Aggregates.group("$time", Accumulators.sum("count", 1)),
                    Aggregates.match(Filters.gt("count", 1))
            );
            AggregateIterable<Document> age = collection.aggregate(bsons);
            MongoCursor<Document> iterator = age.iterator();
            while (iterator.hasNext()) {
                Document next = iterator.next();
                if (next.isEmpty()) continue;
                Object id = next.get("_id");//这里拿到的是time
                Integer count = (Integer) next.get("count");
                //保留一条
                for (int i = 0; i < count - 1; i++) {
                    collection.deleteOne(Filters.eq("time", id));
                }
            }
        }
    }

    /**
     * 用spring mongoTemplate来查询
     * 此方法查询不到数据，哪里的问题
     */
    @GetMapping("useMongoTemplate/checkDuplicateData")
    public Map<String, List<CountResp>> useMongoTemplate() {
        Map<String, List<CountResp>> map = new HashMap<>();
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for (String collectionName : collectionNames) {
            if (!collectionName.startsWith(Constants.POINT_VALUE_PREFIX)) {
                continue;
            }

            AggregationOptions options = new AggregationOptions.Builder().cursor(new Document()).build();
            Aggregation agg = Aggregation.newAggregation(
                    Aggregation.group("time").count().as("count"),
                    Aggregation.match(Criteria.where("count").gt(1))).withOptions(options);
            AggregationResults<CountResp> aggregate = mongoTemplate.aggregate(agg, collectionName, CountResp.class);
            List<CountResp> mappedResults = aggregate.getMappedResults();
            map.put(collectionName, mappedResults);
        }
        return map;
    }

    @Getter
    @Setter
    class CountResp {
        private Long time;
        private Integer count;
    }

    /**
     * db.getCollection('point_value_1').aggregate([{$match:{'time':{'$gte':1562221815606,'$lt':1562221815607}}},{ $group: { _id : null, total: { $sum : '$value' } } }])
     */
    @GetMapping("sum")
    public Map<String, List<String>> sum() {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for (String collectionName : collectionNames) {
            if (!collectionName.startsWith(Constants.POINT_VALUE_PREFIX)) {
                continue;
            }
            List<Bson> bsons = Arrays.asList(
                    Aggregates.match(Filters.gte("time", 1562221815606L)),
                    Aggregates.match(Filters.lt("time", 1562221821614L)),
                    Aggregates.group(null, Accumulators.sum("sum", "$value"))
//                    Aggregates.group(null, Accumulators.avg("avg", "$value"))
            );
            AggregateIterable<Document> age = mongoTemplate.getCollection(collectionName).aggregate(bsons);
            MongoCursor<Document> iterator = age.iterator();
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                list.add(iterator.next().toJson());
            }
            map.put(collectionName, list);
        }
        return map;
    }

    @GetMapping("checkOldDataExists")
    public List<String> checkOldDataExists(Integer times) {
        //循环times次，每次随机取一条数据，查看此数据是否在新的集合中
        List<String> list = new ArrayList<>();
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(times));
        AggregationResults<PointValueDTO> aggregationResults = mongoTemplate.aggregate(aggregation, "pointValueDTO", PointValueDTO.class);
        List<PointValueDTO> mappedResults = aggregationResults.getMappedResults();
        for (PointValueDTO mappedResult : mappedResults) {
            long count = mongoTemplate.getCollection(Constants.POINT_VALUE_PREFIX + mappedResult.getEv_id()).countDocuments(Filters.eq("time", mappedResult.getTime()));
            if (count == 0) {
                list.add(mappedResult.getEv_id() + "---" + mappedResult.getTime());
            }
        }
        return list;
    }

    private static final CodecRegistry DEFAULT_CODEC_REGISTRY =
            fromProviders(asList(new ValueCodecProvider(),
                    new BsonValueCodecProvider(),
                    new DBRefCodecProvider(),
                    new DBObjectCodecProvider(),
                    new DocumentCodecProvider(new DocumentToDBRefTransformer()),
                    new IterableCodecProvider(new DocumentToDBRefTransformer()),
                    new MapCodecProvider(new DocumentToDBRefTransformer()),
                    new GeoJsonCodecProvider(),
                    new GridFSFileCodecProvider()));

    static CodecRegistry pojoCodecRegistry = fromRegistries(DEFAULT_CODEC_REGISTRY,
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    @GetMapping("queryLastData")
    public Long queryLastData() {
        //查询趋势数据中最后一条数据，即time最大的那条
        List<Bson> bsons = Arrays.asList(Aggregates.group(null, Accumulators.max("time", "$time")));
        AggregateIterable<PointValueDTO> aggregate = mongoTemplate.getCollection("hPointValueFiveMin").withCodecRegistry(pojoCodecRegistry).aggregate(bsons, PointValueDTO.class);
        MongoCursor<PointValueDTO> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            PointValueDTO pointValueDTO = iterator.next();
            if (null == pointValueDTO) {
                break;
            }
            return pointValueDTO.getTime();
        }
        return null;
    }

}
