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
    private Geometry geometry;
    private TestGeometryProperties properties;
}
