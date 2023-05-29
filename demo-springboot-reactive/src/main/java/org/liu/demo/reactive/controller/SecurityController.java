package org.liu.demo.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * @Author lzs
 * @Date 2023/5/25 17:35
 **/
@RestController
public class SecurityController {

    @GetMapping("/user")
    public Mono<Principal> getCurrentUser(Mono<Principal> principal){
        return principal;
    }

}
