package org.liu.demo.mongodb.service;

import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;

/**
 * @Author lzs
 * @Date 2023/2/6 15:37
 **/
public class BsonTest {

    @Test
    public void testBson1(){
        StringBuilder sb = new StringBuilder("{$lookup:{");
        sb.append("from: '").append("col_2023").append("',");
        sb.append("localField: '").append("key_a").append("',");
        sb.append("foreignField: '").append("key_a").append("',");
        sb.append("let:{},");
        sb.append("pipeline:[{$project:{");
        sb.append("name:1");
        sb.append("}}],");
        sb.append("as: '").append("a").append("'");
        sb.append("}}");
        Bson bson = Document.parse(sb.toString());
        System.out.println(bson);
    }

    @Test
    public void testBson2(){
        StringBuilder sb = new StringBuilder("{$set:{");
        sb.append("name").append(":").append("1,");
        sb.append("title").append(":").append("1");
        sb.append("}}");
        Document document = Document.parse(sb.toString());
        BsonValue bsonValue = document.toBsonDocument().get("$set");
        BsonValue name = bsonValue.asDocument().remove("name");
        System.out.println(name);
        System.out.println(bsonValue);

        document.replace("$set", bsonValue);
        System.out.println(document);
    }

}
