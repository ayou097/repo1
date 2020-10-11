package cn.tedu.straw.api.question.controller;

import cn.tedu.straw.api.question.util.RedisUtils;
import cn.tedu.straw.commons.util.R;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tags")
public class TagController {

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("")
    public R getTagList(){
        String key = "tags";
        return R.ok(redisUtils.listRange(key));
    }
}
