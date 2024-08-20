package org.liu.demo.mongodb.pojo;

import com.mongodb.client.model.geojson.Geometry;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author lzs
 * @Date 2024/8/16 14:31
 **/
@Data
public class TestGeometry {
    private ObjectId id;
    private String type;
    //这是用mongodb-api的方式
    //注意：如果数据库存储的数据格式必须是Double，不能是NumberDecimal，不然会报错
    private Geometry geometry;
    private TestGeometryProperties properties;
}
