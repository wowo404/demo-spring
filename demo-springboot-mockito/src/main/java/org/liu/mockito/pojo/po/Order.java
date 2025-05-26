package org.liu.mockito.pojo.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author lzs
 * @Date 2022/11/23 15:18
 **/
@Entity
@Table(name = "test_order")
@Data
public class Order {
    @Id
    private Long id;
    private String code;
    private BigDecimal amount;
}
