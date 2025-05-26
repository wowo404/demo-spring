package org.liu.elasticsearch;

import org.junit.jupiter.api.Test;
import org.liu.elasticsearch.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
public class IndexTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void testCreate() {
        boolean success = elasticsearchRestTemplate.indexOps(Item.class).create();
        System.out.println(success);
    }

}
