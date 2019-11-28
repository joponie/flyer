package com.github.joponie.flyer.rocketmq.filter;

import com.github.joponie.flyer.rocketmq.RocketmqProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
public class RedisRepeatFilter implements RepeatFilter {

    /**
     * 过期时间为1小时
     */
    private static final long EXPIRE_TIME = 1;
    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RocketmqProperties rocketmqProperties;

    @Override
    public boolean filterRepeat(String group, String uuid) {
        if (stringRedisTemplate == null) {
            return false;
        }
        String key = String.format(rocketmqProperties.getRedisRepeatKey(), group, uuid);
        return !stringRedisTemplate.opsForValue().setIfAbsent(key, "", EXPIRE_TIME, TimeUnit.HOURS);
    }

    @Override
    public void resetRepeat(String group, String uuid) {
        if (stringRedisTemplate == null) {
            return;
        }
        String key = String.format(rocketmqProperties.getRedisRepeatKey(), group, uuid);
        stringRedisTemplate.delete(key);
    }
}
