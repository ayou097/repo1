package cn.tedu.straw.api.question.mapper;

import cn.tedu.straw.api.question.service.impl.QuestionServiceImpl;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionMapperTests {
    @Autowired
    QuestionMapper questionMapper;
    @Test
    void findHostHitsList(){
        List<QuestionMostHitsVO> hotQuestions = questionMapper.findHostHitsList();
        log.debug("热点问题数量:"+hotQuestions.size());
        for (QuestionMostHitsVO mostHits : hotQuestions) {
            log.debug("热点问题:"+mostHits);
        }
    }
    @Test
    void findByUserId(){
        Integer userId = 8;
        int pageNum = 2;
        int pageSize = 3;
        PageHelper.startPage(pageNum,pageSize);
        List<QuestionListItemVO> questions = questionMapper.findByUserId(userId);
        PageInfo<QuestionListItemVO> pageInfo = new PageInfo<>(questions);
        log.debug("PageInfo >>> {}",pageInfo);
//        log.debug("某用户的id:{},该用户的问题数量:{}",userId,questions.size());
//        for (QuestionListItemVO question : questions) {
//            log.debug(">>> {}",question);
//        }
    }

    @Test
    void findById() {
        Integer id = 8;
        QuestionDetailVO question = questionMapper.findById(id);
        log.debug("question >>> {}", question);
    }

}
