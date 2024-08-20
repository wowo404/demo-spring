package org.liu.demo.mongodb.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.TestGeoJson;
import org.liu.demo.mongodb.pojo.TestGeometry;
import org.liu.demo.mongodb.util.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzs
 * @Date 2024/8/16 14:19
 **/
@SpringBootTest
public class GeometryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
//        String json = "{'type': 'Feature', 'geometry': {'type': 'Point', 'coordinates': [116.43, 39.86] }, 'properties': {'name': 'BeiJing'}}";
        String json = "{'type': 'Feature', 'geometry': {'type': 'Polygon', 'coordinates': [[[120.60,37.38],[119.98,36.65],[121.58,37.00],[120.60,37.38]]] }, 'properties': {'name': 'ShanDong'}}";
        MongoCollection<Document> collection = mongoTemplate.getCollection("test_geometry");
        InsertOneResult result = collection.insertOne(Document.parse(json));
        System.out.println(result.wasAcknowledged() + "，" + result.getInsertedId());
        collection.createIndex(Indexes.geo2dsphere("geometry"));
    }

    //注意：codecRegistry要注册TestGeometry类或TestGeometry所在的包
    @Test
    public void query() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("test_geometry").withCodecRegistry(MongoUtils.getCodecRegistry());
        FindIterable<TestGeometry> iterable = collection.find(Filters.empty(), TestGeometry.class);
        for (TestGeometry testGeometry : iterable) {
            System.out.println(testGeometry);
        }
    }

    //用spring的api插入
    @Test
    public void insertUseSpring() {
        GeoJsonPolygon polygon1 = new GeoJsonPolygon(new Point(120.60, 37.38), new Point(119.98, 36.65), new Point(121.58, 37.00), new Point(120.60, 37.38));
        GeoJsonPolygon polygon2 = new GeoJsonPolygon(new Point(114.767206, 25.686614), new Point(114.756168, 25.681400), new Point(114.774819, 25.678724), new Point(114.767206, 25.686614));
        List<GeoJsonPolygon> polygons = new ArrayList<>();
        polygons.add(polygon1);
        polygons.add(polygon2);
        GeoJsonMultiPolygon multiPolygon = new GeoJsonMultiPolygon(polygons);
        TestGeoJson geoJson = new TestGeoJson();
        geoJson.setType("fuck");
        geoJson.setGeoJson(multiPolygon);
        TestGeoJson insert = mongoTemplate.insert(geoJson);
        System.out.println(insert);
    }

    //用spring的api查询--------不建议使用
    @Test
    public void queryUseSpring() {
        List<TestGeoJson> testGeoJsons = mongoTemplate.find(new Query(), TestGeoJson.class);
        System.out.println(testGeoJsons);
    }
}
