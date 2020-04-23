package org.liu.lettuce.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

//@Configuration
public class CacheConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private Integer database;
    @Value("redis.timeout")
    private String timeout;
    @Value("redis.shutdownTimeout")
    private String shutdownTimeout;
    @Value("${redis.lettuce.pool.maxActive}")
    private Integer maxActive;
    @Value("${redis.lettuce.pool.maxIdle}")
    private Integer maxIdle;
    @Value("${redis.lettuce.pool.maxAwait}")
    private Integer maxAwait;
    @Value("${redis.lettuce.pool.minIdle}")
    private Integer minIdle;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
//        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder().build();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxAwait);
        LettucePoolingClientConfiguration poolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.parse(timeout)).shutdownTimeout(Duration.parse(shutdownTimeout)).poolConfig(poolConfig).build();

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(RedisPassword.of(password));
        configuration.setDatabase(database);
        return new LettuceConnectionFactory(configuration, poolingClientConfiguration);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

}
