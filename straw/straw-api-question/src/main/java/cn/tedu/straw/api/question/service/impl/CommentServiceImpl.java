package cn.tedu.straw.api.question.service.impl;

import cn.tedu.straw.api.question.dto.PostCommentDTO;
import cn.tedu.straw.api.question.mapper.AnswerMapper;
import cn.tedu.straw.api.question.mapper.CommentMapper;
import cn.tedu.straw.api.question.service.ICommentService;
import cn.tedu.straw.commons.ex.AnswerNotFoundException;
import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.model.Comment;
import cn.tedu.straw.commons.vo.AnswerListItemVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void post(PostCommentDTO postCommentDTO, Integer userId, String userNickName) {
        // 基于参数answerId调用answerMapper.selectById()查询“答案”数据
        Answer anwers = answerMapper.selectById(postCommentDTO.getAnswerId());
        // 判断查询到的“答案”是否为null
        if(anwers == null){
            // 是：抛出AnswerNotFoundException
            throw new AnswerNotFoundException("评论失败!该问题答案已删除!");
        }
        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();
        // 创建Comment对象
        Comment comment = new Comment()
            // 向Comment对象中补全数据：userId, userNickName < 参数
            .setUserId(userId)
            .setUserNickName(userNickName)
            // 向Comment对象中补全数据：answerId < 参数
            .setAnswerId(postCommentDTO.getAnswerId())
            // 向Comment对象中补全数据：content < 参数
            .setContent(postCommentDTO.getContent())
            // 向Comment对象中补全数据：gmtCreate, gmtModified < now
            .setGmtCreate(now)
            .setGmtModified(now);
        // 基于以上Comment对象调用commentMapper.insert()插入评论数据，并获取返回值
        int rows = commentMapper.insert(comment);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发表评论失败!请稍后再试!");
        }
    }
}
