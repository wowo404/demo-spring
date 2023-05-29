package org.liu.demo.reactive.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @Author lzs
 * @Date 2023/2/14 17:30
 **/
@Table
@Data
public class Student {
    @Id
    private Long id;
    private String name;
    private LocalDateTime fuckTime;
    private Instant birthday;
}
