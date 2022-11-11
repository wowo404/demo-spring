package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @Author lzs
 * @Date 2022/11/11 9:54
 **/
@Data
public class CompanyMovie {
    private String id;
    private ObjectId companyId;
    private ObjectId movieId;
}
