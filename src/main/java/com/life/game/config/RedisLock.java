package com.life.game.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate srt;

    /**
     * 加锁 ,应用中当调用当前方法返回if(!lock)时建议抛出异常
     *
     * @param key          key
     * @param expectedTime 期望时间值 = 当前系统毫秒值 + 业务容忍超时时间
     * @return 加锁是否成功
     */
    public boolean lock(String key, String expectedTime) {
        /* setIfAbsent方法参考:http://redis.cn/commands/hsetnx.html
         *  个人理解: 当key不存在时添加redis列值为value并返回true,如果存在则直接返回false
         * */
        //当不存在key的一列时候设置value并返回true
        if (srt.opsForValue().setIfAbsent(key, expectedTime)) {
            return true;
        }
        //判断是否超时
        String oldTime = srt.opsForValue().get(key);
        long currentTimeMillis = System.currentTimeMillis();
        if (!StringUtils.isEmpty(oldTime) && currentTimeMillis > Long.parseLong(oldTime)) {
            String oldValue = srt.opsForValue().getAndSet(key, expectedTime); //利用redis是单线程的特性
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(oldTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁 删除指定key的redis列
     *
     * @param key key
     */
    public void unlock(String key, String expectedTime) {
        String value = srt.opsForValue().get(key);
        if (!StringUtils.isEmpty(value) && value.equals(expectedTime)) {
            srt.opsForValue().getOperations().delete(key);
        }
    }
}
