package org.liu.mockito.pojo.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author lzs
 * @Date 2022/11/23 15:18
 **/
@Data
public class Order {
    private Long id;
    private String code;
    private BigDecimal amount;
}
