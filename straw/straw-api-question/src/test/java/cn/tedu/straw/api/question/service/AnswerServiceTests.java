package cn.tedu.straw.api.question.service;

import cn.tedu.straw.api.question.dto.PostAnswerDTO;
import cn.tedu.straw.commons.ex.ServiceException;
import cn.tedu.straw.commons.vo.AnswerListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AnswerServiceTests {
    @Autowired
    IAnswerService service;
    @Test
    void postAnswer(){
        try{
            PostAnswerDTO postAnswer = new PostAnswerDTO()
                    .setContent("这个也没什么区别?百度就行...")
                    .setQuestionId(112);
            Integer userId = 8;
            String userNickName = "安琪拉";
            service.post(postAnswer,userId,userNickName);
            log.debug("发布答案成功！");
        }catch (ServiceException e){
            log.debug("发布答案失败！错误类型：{}，问题原因：{}", e.getClass().getName(),e.getMessage());
        }
    }
    @Test
    void getAnswerList(){
        Integer questionId = 32;
        List<AnswerListItemVO> answers = service.getAnswerList(questionId);
        log.debug("根据问题id={}查询到{}个答案: ",questionId,answers.size());
        for (AnswerListItemVO answer : answers) {
            log.debug("答案 >>> {}",answer);
        }
    }
}
