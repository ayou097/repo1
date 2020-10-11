package cn.tedu.straw.api.question.service.impl;

import cn.tedu.straw.api.question.dto.PostAnswerDTO;
import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.mapper.AnswerMapper;
import cn.tedu.straw.api.question.mapper.QuestionMapper;
import cn.tedu.straw.api.question.service.IAnswerService;
import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.ex.QuestionNotFoundException;
import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.AnswerListItemVO;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public void post(PostAnswerDTO postAnswerDTO, Integer userId, String userNickName) {
        // 基于参数questionId调用questionMapper.findById()查询“问题”数据
        QuestionDetailVO question = questionMapper.findById(postAnswerDTO.getQuestionId());
        // 判断以上查询结果是否为null
        if (question == null) {
            // 是：抛出QuestionNotFoundException
            throw new QuestionNotFoundException("发布失败!该问题已删除!");
        }

        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();
        // 创建Answer对象
        Answer answer = new Answer()
            // 补全Answer对象的属性：content < 参数
            .setContent(postAnswerDTO.getContent())
            // 补全Answer对象的属性：userId, userNickName < 参数
            .setUserId(userId)
            .setUserNickName(userNickName)
            // 补全Answer对象的属性：questionId < 参数
            .setQuestionId(postAnswerDTO.getQuestionId())
            // 补全Answer对象的属性：isAccepted < 0
            .setIsAccepted(0)
            // 补全Answer对象的属性：gtmCreate, gmtModified < now
            .setGmtCreate(now)
            .setGmtModified(now);
        // 基于Answer对象调用answerMapper.insert()插入“答案”数据，并获取返回值
        int rows = answerMapper.insert(answer);
        // 判断以上返回值是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发布失败!请稍后再试!");
        }

    }

    @Override
    public List<AnswerListItemVO> getAnswerList(Integer questionId) {
        return answerMapper.findByQuestionId(questionId);
    }
}
