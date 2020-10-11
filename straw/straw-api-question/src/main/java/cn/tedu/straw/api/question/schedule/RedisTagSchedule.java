package cn.tedu.straw.api.question.schedule;

import cn.tedu.straw.api.question.service.IQuestionService;
import cn.tedu.straw.api.question.service.ITagService;
import cn.tedu.straw.api.question.util.RedisUtils;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import cn.tedu.straw.commons.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class RedisTagSchedule {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ITagService tagService;
    @Autowired
    IQuestionService questionService;

    @Scheduled(fixedRate = 10*60*1000)
    public void updateRedisTag(){
        log.debug("[{}]准备更新Redis服务器中缓存的标签列表", DateTimeFormatter.ofPattern("yyyy-HH-dd HH:mm:ss").format(LocalDateTime.now()));
        //在Redis中"标签列表"数据的key
        String tagListKey = "tags";
        //删除"标签列表"数据,避免反复增加列表元素
        redisUtils.delete(tagListKey);
        //从数据库中读取新的“标签列表”
        List<TagVO> tags = tagService.getTagList();
        // 遍历“标签列表”并向Redis中逐一添加数据
        for (TagVO tag : tags) {
            // 向Redis中添加数据
            redisUtils.rightPush(tagListKey,tag);
            redisUtils.set("tag"+tag.getId(),tag);
        }
        //日志
        log.debug("[{}]更新Redis服务器中缓存的标签列表,完成!",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

        //在Redis中"热点问题列表"数据的key
        String questionListKey = "hostQuestions";
        //删除"热点问题列表"数据,避免反复增加列表元素
        redisUtils.delete(questionListKey);
        //从数据库中读取新的"热点问题列表"
        List<QuestionMostHitsVO> hostQuestions = questionService.getHotHitsQuestions();
        //遍历"热点问题列表"并向redis中逐一添加数据
        for (QuestionMostHitsVO hostQuestion : hostQuestions) {
            //向redis中添加数据
            redisUtils.rightPush(questionListKey,hostQuestion);
        }
        //日志
        log.debug("[{}]更新Redis服务器缓存的热点问题列表,完成!",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }

}
