package org.liu.demo.mongodb.support;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.liu.demo.mongodb.constant.Constants;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class MongoDocumentHelper {

    private final MongoTemplate mongoTemplate;

    /**
     * 插入文档并自动填充审计字段
     */
    public void insertWithAuditing(String collectionName, Document document) {
        LocalDateTime now = LocalDateTime.now();

        // 设置审计字段
        if (!document.containsKey(Constants.CREATE_TIME)) {
            document.put(Constants.CREATE_TIME, now);
        }
        document.put(Constants.UPDATE_TIME, now);

        mongoTemplate.getCollection(collectionName).insertOne(document);
    }

    /**
     * 更新文档并自动填充审计字段
     */
    public void updateWithAuditing(String collectionName, Document filter, Document update) {
        LocalDateTime now = LocalDateTime.now();

        // 添加更新时间和更新人
        Document updateDoc = new Document("$set", new Document().append(Constants.UPDATE_TIME, now));

        // 合并原有的更新内容
        if (update != null) {
            updateDoc.get("$set", Document.class).putAll(update);
        }

        mongoTemplate.getCollection(collectionName).updateOne(filter, updateDoc);
    }
}
