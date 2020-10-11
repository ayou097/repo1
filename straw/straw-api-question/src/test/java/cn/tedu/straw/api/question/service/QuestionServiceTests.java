package cn.tedu.straw.api.question.service;

import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.commons.ex.ServiceException;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionServiceTests {

    @Autowired
    IQuestionService service;
    @Test
    void postQuestion(){
        try{
            PostQuestionDTO postQuestionDTO = new PostQuestionDTO()
                    .setTitle("数据库索引有哪些?")
                    .setContent("今天把面试官吊打了,~~~！")
                    .setTagIds(new Integer[]{1,3,5})
                    .setTeacherIds(new Integer[]{2,7,9});
            Integer userId = 20;
            String userNickName = "葫芦娃";
            service.postQuestion(postQuestionDTO,userId,userNickName);
            log.debug("发布问题成功！");
        } catch (ServiceException e){
            log.debug("发布问题失败！问题类型：{}，问题原因：{}", e.getClass().getName(),e.getMessage());
        }
    }

    @Test
    void getHotHitsQuestions(){
        List<QuestionMostHitsVO> hotQuestions = service.getHotHitsQuestions();
        log.debug("热点问题数量:"+hotQuestions.size());
        for (QuestionMostHitsVO hotQuestion : hotQuestions) {
            log.debug(">>>"+hotQuestion);
        }
    }
    @Test
    void getMyQuestions(){
        Integer pageNum = 1;
        Integer userId = 8;
        PageInfo pageInfo =  service.getMyQuestions(pageNum,userId);
        log.debug("pageInfo >>> {}",pageInfo);
    }

    @Test
    void getQuestionDetail() {
        try {
            Integer id = 100;
            QuestionDetailVO question = service.getQuestionDetail(id);
            log.debug("question >>> {}", question);
        } catch (ServiceException e) {
            log.debug("查询问题详情失败，错误类型：{}", e.getClass().getName());
            log.debug("错误原因：{}", e.getMessage());
        }
    }

}
