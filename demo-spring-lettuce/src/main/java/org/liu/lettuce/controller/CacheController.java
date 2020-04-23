package org.liu.lettuce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("cache")
@RestController
public class CacheController {

    @Autowired
    private RedisTemplate<String, Object> jacksonRedisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("queryValue")
    public Object queryValue(String key){
        Object o = stringRedisTemplate.boundValueOps(key).get();
        return o;
    }

    @GetMapping("queryHash")
    public void queryHash(String key){
        Map<Object, Object> entries = jacksonRedisTemplate.boundHashOps(key).entries();
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }
    }

    @GetMapping("queryList")
    public void queryList(String key){
        List<Object> list = jacksonRedisTemplate.boundListOps(key).range(0, Integer.MAX_VALUE);
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
