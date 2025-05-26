package org.liu.demo.mongodb.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author lzs
 * @Date 2024/4/7 17:12
 **/
@Document
@Data
public class Inventory {
    private String id;
    private String item;
    private Integer qty;
    private String[] tags;
    private InventorySize size;
}
