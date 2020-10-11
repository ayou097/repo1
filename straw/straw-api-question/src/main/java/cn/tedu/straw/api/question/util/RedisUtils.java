package cn.tedu.straw.api.question.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 操作Redis服务器中的数据的工具类
 */
@Component
public class RedisUtils {
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 删除Redis中的某个数据
     * @param key 被删除的key
     * @return 删除操作是否成功
     */
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 向redis中存入数据
     * @param key 在Redis中存入数据时使用的key
     * @param value 存入的值
     */
    public void set(String key,Serializable value){
        ValueOperations<String,Serializable> ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }

    /**
     * 从Redis中取出数据
     * @param key 此前存入数据使用的key
     * @return 在Redis中参数key匹配的值
     */
    public Serializable get(String key){
        ValueOperations<String,Serializable> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
    /**
     * 向Redis中的某个List集合中添加集合元素
     * @param key 在Redis中的List集合类型的数据的key
     * @param value 需要存入到Redis的List集合的元素值
     * @return
     */
    public Long rightPush(String key,Serializable value){
        ListOperations<String,Serializable> ops = redisTemplate.opsForList();
        return ops.rightPush(key,value);
    }

    /**
     * 获取Redis中某LIst集合
     * @param key
     * @return
     */
    public List<Serializable> listRange(String key){
        ListOperations<String,Serializable> ops = redisTemplate.opsForList();
        long start = 0;
        long end = ops.size(key);
        return ops.range(key,start,end);
    }

    /**
     * 获取Redis集合中的数据片段
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Serializable> listRange(String key,long start,long end){
        ListOperations<String,Serializable> ops = redisTemplate.opsForList();
        return ops.range(key,start,end);
    }
}
