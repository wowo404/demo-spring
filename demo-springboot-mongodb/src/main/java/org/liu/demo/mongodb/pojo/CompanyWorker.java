package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @Author lzs
 * @Date 2022/11/8 14:20
 **/
@Data
public class CompanyWorker {
    @MongoId
    private String id;
    @Field(targetType = FieldType.OBJECT_ID)
    private String companyId;
    @Field(targetType = FieldType.OBJECT_ID)
    private String workerId;
}
