package cn.tedu.straw.api.question;

import cn.tedu.straw.api.question.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;

@SpringBootTest
@Slf4j
class StrawApiQuestionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisUtils redisUtils;
    @Test
    void listRange(){
        String key = "tags";
        List<Serializable> list = redisUtils.listRange(key);
        log.debug("Key为{}的List集合长度为:{}",key,list.size());
        for (Serializable serializable : list) {
            log.debug(">>> {}",serializable);
        }
    }

}
