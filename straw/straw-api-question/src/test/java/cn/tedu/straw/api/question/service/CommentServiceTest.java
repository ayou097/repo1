package cn.tedu.straw.api.question.service;

import cn.tedu.straw.api.question.dto.PostAnswerDTO;
import cn.tedu.straw.api.question.dto.PostCommentDTO;
import cn.tedu.straw.api.question.mapper.CommentMapper;
import cn.tedu.straw.commons.ex.ServiceException;
import cn.tedu.straw.commons.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommentServiceTest {
    @Autowired
    ICommentService service;
    @Test
    void post(){
        try{
            PostCommentDTO postCommentDTO = new PostCommentDTO()
                    .setContent("测试评论6号答案")
                    .setAnswerId(60);
            Integer userId = 9;
            String userNickName = "洛璃";
            service.post(postCommentDTO,userId,userNickName);
            log.debug("发布评论成功！");
        }catch (ServiceException e){
            log.debug("发布评论失败！错误类型：{}，问题原因：{}", e.getClass().getName(),e.getMessage());
        }
    }
}
