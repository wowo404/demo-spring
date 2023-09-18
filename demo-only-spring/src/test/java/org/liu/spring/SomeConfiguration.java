package org.liu.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lzs
 * @Date 2023/9/5 14:27
 **/
@Configuration
public class SomeConfiguration {
    @Bean
    public MockKeyValueTemplate<String, String> stringKeyValueTemplate() {
        return new MockKeyValueTemplate<>();
    }

    @Bean
    public MockKeyValueTemplate<String, Object> objectKeyValueTemplate() {
        return new MockKeyValueTemplate<>();
    }

    @Bean
    public Map<String, Map<String, Integer>> nestedMap() {
        return new HashMap<>();
    }
}

