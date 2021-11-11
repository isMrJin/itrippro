package com.itrip.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/9 9:37
 */
public class RedisAPI {
    public JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    //get
    public String get(String key){
        Jedis resource = jedisPool.getResource();
        String value = resource.get(key);
       jedisPool.returnResource(resource);
       return  value;
    }
    //set
    public String set(String key,String  value){
        Jedis resource = jedisPool.getResource();
        String result = resource.set(key,value);
        jedisPool.returnResource(resource);
        return  result;
    }
    public String set(String key,int expire,String  value){
        Jedis resource = jedisPool.getResource();
        String result = resource.setex(key, expire, value);
        jedisPool.returnResource(resource);
        return  result;
    }
    //exist
    public boolean exist(String key){
        Jedis resource = jedisPool.getResource();
        boolean result = resource.exists(key);
        jedisPool.returnResource(resource);
        return result;
    }
    //ttl
    public Long ttl(String key){
        Jedis resource = jedisPool.getResource();
        Long result = resource.ttl(key);
        jedisPool.returnResource(resource);
        return result;
    }
    //del
    public Long del(String key){
        Jedis resource = jedisPool.getResource();
        Long result = resource.del(key);
        jedisPool.returnResource(resource);
        return result;
    }
}
