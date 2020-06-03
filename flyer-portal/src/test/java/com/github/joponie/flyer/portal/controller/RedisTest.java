package com.github.joponie.flyer.portal.controller;

import com.github.joponie.flyer.common.util.JsonUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author kain
 * @since 2019-11-26
 */
public class RedisTest {

    private static Jedis jedis;        //单实例[]
    private static ShardedJedis shard;        //分片[]
    private static ShardedJedisPool pool;    //池化[apache common - pool2]

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //单个节点
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("redis-1.0");
        //分片
        List<JedisShardInfo> shards = Collections.singletonList(
                new JedisShardInfo("192.168.1.115", 6379));

        shard = new ShardedJedis(shards);
        //池化
        GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
        goConfig.setMaxTotal(100);
        goConfig.setMaxIdle(20);
        goConfig.setMaxWaitMillis(-1);
        goConfig.setTestOnBorrow(true);
        pool = new ShardedJedisPool(goConfig, shards);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        jedis.disconnect();
        shard.disconnect();
        pool.destroy();
    }

    @Test
    public void lua1() {
        for (int i = 0; i < 10; i++) {
            jedis.hset("number", "f" + i, String.valueOf(0));
        }
        jedis.hgetAll("number");
        String script = "redis.log(redis.LOG_WARNING,'start');\n" +
                "local keys = redis.call('HKEYS', KEYS[1]);\n" +
                "local result = 0;\n" +
                "for k, v in ipairs(keys) do\n" +
                "    redis.log(redis.LOG_WARNING, math.random(0, 100));\n" +
                "    local hval = redis.call('HGET', KEYS[1], v);\n" +
                "    redis.log(redis.LOG_WARNING, hval);\n" +
                "    result = result + hval;\n" +
                "end\n" +
                "redis.log(redis.LOG_WARNING,'end');\n" +
                "return result;";
        Object number = jedis.eval(script, 1, "number");
        System.out.println(number);
    }

    @Test
    public void lua2() {
        for (AwardSettingCache awardSettingCache : caches()) {
            jedis.hset("json", String.valueOf(awardSettingCache.getId()), JsonUtils.toJson(awardSettingCache));
        }
        String script = "redis.log(redis.LOG_WARNING,'start');\n" +
                "local function hgetall(hash_key)\n" +
                "    local flat_map = redis.call('HGETALL', hash_key)\n" +
                "    local result = {}\n" +
                "    for i = 1, #flat_map, 2 do\n" +
                "        result[flat_map[i]] = flat_map[i + 1]\n" +
                "    end\n" +
                "    return result\n" +
                "end\n" +
                "redis.log(redis.LOG_WARNING,'for');\n" +
                "local all = hgetall( KEYS[1]);\n" +
                "for k, v in ipairs(all) do\n" +
                "    redis.log(redis.LOG_WARNING, 'key:' .. k .. \"value:\" .. v);\n" +
                "end\n" +
                "redis.log(redis.LOG_WARNING,'for');\n" +
                "return type(all);";
        Object json = jedis.eval(script, 1, "json");
        System.out.println(json);
    }

    private List<AwardSettingCache> caches() {
        List<AwardSettingCache> caches = new ArrayList<>();
        AwardSettingCache a1 = new AwardSettingCache();
        a1.setId(3);
        a1.setAwardName("三等奖");
        a1.setAwardRate(BigDecimal.valueOf(50));
        a1.setNum(999);
        caches.add(a1);
        AwardSettingCache a2 = new AwardSettingCache();
        a2.setId(2);
        a2.setAwardName("二等奖");
        a2.setAwardRate(BigDecimal.valueOf(40));
        a2.setNum(20);
        caches.add(a2);
        AwardSettingCache a3 = new AwardSettingCache();
        a3.setId(1);
        a3.setAwardName("一等奖");
        a3.setAwardRate(BigDecimal.valueOf(10));
        a3.setNum(5);
        caches.add(a3);
        return caches;
    }

}
