package com.example.demo.redis;

import com.sun.javafx.logging.PulseLogger;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Leo liang
 * @Date 2022/3/26
 *
 * 1、获取锁--单个原子性操作
 * SET dlock my_random_value NX PX 30000
 * 2、释放锁--lua脚本-保证原子性+单线程，从而具有事务性
 * if redis.call("get",KEYS[1]) == ARGV[1] then
 * return redis.call("del",KEYS[1])
 * else
 * return 0
 * end
 *
 */
@Data
@Component
public class RedisLockUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private static RedisTemplate myRedisTemplate;



    public static boolean tryLock(String lockId, long timeout, String uuid) {
        return myRedisTemplate.opsForValue().setIfAbsent(lockId, uuid, timeout, TimeUnit.SECONDS);
    }

    public static String populateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static boolean unLock(String lockId, String lockValue) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis.lua")));
        long s = (long) myRedisTemplate.execute(script, Arrays.asList(lockId), lockValue);
        System.out.println("result ----> " + s);
        return s != 0;
    }

    //每次进来减1
    //由于前面已经被锁住了，所以不需要在里面做任何逻辑
    public static void subOrder(String orderKey) {
        myRedisTemplate.opsForValue().decrement(orderKey);
    }

    @PostConstruct
    public void setMyRedisTemplate() {
        myRedisTemplate = redisTemplate;
    }
}
