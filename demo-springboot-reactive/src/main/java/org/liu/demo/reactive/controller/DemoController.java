package org.liu.demo.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author lzs
 * @Date 2023/2/13 15:03
 **/
@RestController
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        long start = System.currentTimeMillis();
        String helloStr = getHelloStr();
        System.out.println("普通接口耗时：" + (System.currentTimeMillis() - start));
        return helloStr;
    }

    @GetMapping("/hello2")
    public Mono<String> hello2() {
        long start = System.currentTimeMillis();
        Mono<String> hello2 = Mono.fromSupplier(this::getHelloStr);
        System.out.println("WebFlux 接口耗时：" + (System.currentTimeMillis() - start));
        return hello2;
    }

    @GetMapping(value = "/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        return Flux.fromArray(new String[]{"javaboy","itboyhub","www.javaboy.org","itboyhub.com"}).map(s -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "my->data->" + s;
        });
    }

    private String getHelloStr() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
