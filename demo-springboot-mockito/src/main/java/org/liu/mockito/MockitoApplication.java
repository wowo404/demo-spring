package org.liu.mockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lzs
 * @Date 2022/11/19 14:07
 **/
@SpringBootApplication
public class MockitoApplication {
    public static void main(String[] args) {
        System.setProperty("user.timezone", "UTC");
        SpringApplication.run(MockitoApplication.class, args);
    }
}
