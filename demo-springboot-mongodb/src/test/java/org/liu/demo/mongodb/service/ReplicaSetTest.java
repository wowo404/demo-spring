package org.liu.demo.mongodb.service;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Inventory;
import org.liu.demo.mongodb.pojo.InventorySize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lzs
 * @Date 2024/4/7 17:25
 **/
@SpringBootTest
public class ReplicaSetTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void add() {
        InventorySize inventorySize = new InventorySize();
        inventorySize.setH(13.5);
        inventorySize.setW(3.5);
        inventorySize.setUom("one");
        Inventory inventory = new Inventory();
        inventory.setItem("phone");
        inventory.setQty(6000);
        inventory.setTags(new String[]{"iphone", "xiaomi"});
        inventory.setSize(inventorySize);
        mongoTemplate.insert(inventory);
    }

    @Test
    public void query() {
        Inventory inventory = mongoTemplate.findById("66125dce38cf76ead47b2da9", Inventory.class);
        System.out.println(inventory);
    }

}
