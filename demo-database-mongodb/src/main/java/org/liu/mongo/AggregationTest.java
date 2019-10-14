package org.liu.mongo;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//import com.mongodb.MongoClientSettings;

public class AggregationTest {

    static Block<Document> printBlock = document -> System.out.println(document.toJson());
//
//    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
//            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    public static MongoDatabase connect2() {
        String uri = "mongodb://root:root@192.168.17.230/logistics_test?w=majority";
//        String uri = "mongodb://user_test:user_test@127.0.0.1:27817/manufacture_test?w=majority";
//        ConnectionString connString = new ConnectionString(uri);
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connString)
//                .retryWrites(true)
//                .codecRegistry(pojoCodecRegistry)
//                .build();
//        com.mongodb.client.MongoClient mongoClient = MongoClients.create(settings);
//        return mongoClient.getDatabase("logistics_test");
        String host = "192.168.17.230";
        int port = 27017;
        String db = "logistics_test";
        String username = "root";
        String password = "root";
        List<ServerAddress> addresses = new ArrayList<>();
        ServerAddress address = new ServerAddress(host, port);
        addresses.add(address);

        List<MongoCredential> credentials = new ArrayList<>();
        MongoCredential credential = MongoCredential.createCredential(username, db, password.toCharArray());
        credentials.add(credential);

        MongoClient client = new MongoClient(addresses, credentials, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        return client.getDatabase(db);
    }

    public static void insert(){
        List<MongoSaveModel.InnerModel> innerModels = new ArrayList<>();

        MongoSaveModel.InnerModel innerModel = new MongoSaveModel.InnerModel();
        innerModel.setId(1);
        innerModel.setName("zhang");
        MongoSaveModel.InnerModel innerModel1 = new MongoSaveModel.InnerModel();
        innerModel1.setId(2);
        innerModel1.setName("liu");

        innerModels.add(innerModel);
        innerModels.add(innerModel1);

        MongoSaveModel model = new MongoSaveModel();
        model.setSerial((short) 1558);
        model.setTitle("终结者");
        model.setAge(1200);
        model.setScore(128.65f);
        model.setSalary(9898.99);
        model.setOk(true);
        model.setCreateTime(new Date());
        model.setAmount(new BigDecimal("987654321.258"));
        //WARNING:mongo自带的codec转换器，无法转换BigInteger和array
//        model.setGalaxyNumber(new BigInteger("9517569874123"));
        model.setInnerModels(innerModels);
//        model.setWorkIds(new Integer[]{1,2,3,4,5,6});

        MongoCollection<MongoSaveModel> collection = connect2().getCollection("hello", MongoSaveModel.class);
        collection.insertOne(model);
    }

    public static void agg() {
        MongoCollection<Document> collection = connect2().getCollection("person");
        AggregateIterable<Document> age = collection.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("age", 21)),
                        Aggregates.group(null, Accumulators.sum("sum", "$salary"))
                )
        );
        MongoCursor<Document> iterator = age.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJson());
        }
    }

    public static void query(){
        MongoCollection<PointValueDTO> collection = connect2().getCollection("trend_data_five_min_6483", PointValueDTO.class);
        Filters.and(Filters.gte("time", 1570687800000L), Filters.lt("time", 1570687850000L));
        FindIterable<PointValueDTO> iterable = collection.find(Filters.and(Filters.gte("time", 1570687800000L), Filters.lt("time", 1570687850000L)));
        MongoCursor<PointValueDTO> mongoCursor = iterable.iterator();
        while (mongoCursor.hasNext()) {
            PointValueDTO next = mongoCursor.next();
            System.out.println(next);
        }
    }

    public static void querySample(){
        AggregateIterable<MongoSaveModel> agg = connect2().getCollection("hello")
                .withCodecRegistry(pojoCodecRegistry).aggregate(Arrays.asList(Aggregates.sample(1)), MongoSaveModel.class);
        MongoCursor<MongoSaveModel> iterator = agg.iterator();
        while (iterator.hasNext()) {
            MongoSaveModel next = iterator.next();
            System.out.println(next);
        }

    }

    public static void main(String[] args) {
        query();
    }

}
