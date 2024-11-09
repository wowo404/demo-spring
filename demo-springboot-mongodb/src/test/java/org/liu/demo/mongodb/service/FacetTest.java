package org.liu.demo.mongodb.service;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lzs
 * @Date 2024/11/8 17:24
 **/
@SpringBootTest
public class FacetTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void getResult() {
        List<Document> results = new ArrayList<>();
        FindIterable<Document> findIterable = mongoTemplate.getCollection("test_facet622").find();
        Document document = findIterable.iterator().next();
        for (String key : document.keySet()) {
            if (key.equals("_id")) {
                continue;
            }
            List<Document> list = document.getList(key, Document.class);
            for (Document document1 : list) {
                Document matchedDocument = findMatchedDocument(results, "county", document1.getString("_id"));
                Set<String> keySet = document1.keySet();
                for (String key1 : keySet) {
                    if (key1.equals("_id")) {
                        continue;
                    }
                    matchedDocument.put(key1, document1.get(key1));
                }
            }
        }
        for (Document result : results) {
            System.out.println(result);
        }
    }

    private Document findMatchedDocument(List<Document> results, String groupByKey, String value) {
        for (Document result : results) {
            if (result.containsKey(groupByKey) && result.containsValue(value)) {
                return result;
            }
        }
        Document newDoc = new Document();
        newDoc.put(groupByKey, value);
        results.add(newDoc);
        return newDoc;
    }
}
