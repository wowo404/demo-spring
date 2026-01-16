package org.liu.mockito.pojo.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.*;

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
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Instant instant;
    private ZonedDateTime zonedDateTime;
    private OffsetDateTime offsetDateTime;
}
