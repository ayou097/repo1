package cn.tedu.straw.api.question.service;

import cn.tedu.straw.api.question.dto.PostAnswerDTO;
import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.mapper.QuestionMapper;
import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.vo.AnswerListItemVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
public interface IAnswerService extends IService<Answer> {
    /**
     * 发布答案
     * @param postAnswerDTO
     * @param userId
     * @param userNickName
     */
    void post(PostAnswerDTO postAnswerDTO, Integer userId, String userNickName);

    /**
     *
     * @param questionId
     * @return
     */
    List<AnswerListItemVO> getAnswerList(Integer questionId);
}
