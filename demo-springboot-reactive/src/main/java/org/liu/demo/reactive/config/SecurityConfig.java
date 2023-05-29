package org.liu.demo.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author lzs
 * @Date 2023/5/25 17:51
 **/
//@Configuration
public class SecurityConfig {
    @Bean
    MapReactiveUserDetailsService mapReactiveUserDetailsService() {
        UserDetails ud1 = User.withUsername("admin")
                .password("{noop}123")
                .roles("admin")
                .build();
        UserDetails ud2 = User.withUsername("zhangsan")
                .password("{noop}123")
                .roles("user")
                .build();
        return new MapReactiveUserDetailsService(ud1, ud2);
    }
}
