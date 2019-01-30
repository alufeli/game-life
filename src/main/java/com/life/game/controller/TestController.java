package com.life.game.controller;

import com.life.game.config.result.RestResult;
import com.life.game.config.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * redis 缓存测试，插入指定key和值
     * @return
     */
    @RequestMapping("/redisHandler")
    public String redisHandler(){
        stringRedisTemplate.opsForValue().set("k5", "Springboot redis");
        return stringRedisTemplate.opsForValue().get("k5");
    }

    @RequestMapping("/")
    public RestResult<Object> test() {
        return  ResultGenerator.createSucResult();
    }
}
