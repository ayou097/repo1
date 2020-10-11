package cn.tedu.straw.api.question.mapper;

import cn.tedu.straw.commons.vo.AnswerListItemVO;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AnswerMapperTests {
    @Autowired
    AnswerMapper answerMapper;
    @Test
    void findByQuestionId(){
        Integer questionId = 32;
        List<AnswerListItemVO> answers = answerMapper.findByQuestionId(questionId);
        log.debug("根据问题id={}查询到{}个答案: ",questionId,answers.size());
        for (AnswerListItemVO answer : answers) {
            log.debug("答案 >>> {}",answer);
        }
    }
}
