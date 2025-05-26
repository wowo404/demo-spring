package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJson;

/**
 * @author lzs
 * @Date 2024/8/16 14:31
 **/
@Data
public class TestGeoJson {
    private String id;
    private String type;
    //这是spring-data-mongodb的方式，此方式的便捷之处在于查询的时候spring已做好了数据转换pojo，不足之处在于此方式序列化后的json有很多的重复数据（经纬度重复了一遍）
    private GeoJson<?> geoJson;
}
