package org.liu.mockito.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.liu.mockito.support.CustomInstantDeserializer;
import org.liu.mockito.support.CustomInstantSerializer;
import org.liu.mockito.support.CustomLocalDateTimeDeserializer;
import org.liu.mockito.support.CustomLocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * jackson 配置
 *
 * @author selfcc
 */
@Slf4j
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.findModulesViaServiceLoader(true);
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            builder.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            // 自定义 LocalDateTime 格式
            builder.serializerByType(LocalDateTime.class, CustomLocalDateTimeSerializer.INSTANCE);
            builder.deserializerByType(LocalDateTime.class, CustomLocalDateTimeDeserializer.INSTANCE);
            builder.serializerByType(Instant.class, CustomInstantSerializer.INSTANCE);
            builder.deserializerByType(Instant.class, CustomInstantDeserializer.INSTANCE);
            log.info("初始化 jackson 配置");
        };
    }

}
