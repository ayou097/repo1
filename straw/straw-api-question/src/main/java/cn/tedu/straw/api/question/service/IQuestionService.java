package cn.tedu.straw.api.question.service;

import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-16
 */
public interface IQuestionService extends IService<Question> {
    /**
     * 发布问题
     * @param postQuestionDTO 客户端提交的参数
     * @param userId
     * @param userNickName
     */
    void postQuestion(PostQuestionDTO postQuestionDTO,Integer userId,String userNickName);

    /**
     * 查询热点问题列表
     * @return
     */
    List<QuestionMostHitsVO> getHotHitsQuestions();

    /**
     * 获取当前登录的用户问答列表
     * @param pageNum 页码
     * @param userId 用户id
     * @return 该用户的问答列表
     */
    PageInfo<QuestionListItemVO> getMyQuestions(Integer pageNum,Integer userId);


    QuestionDetailVO getQuestionDetail(Integer id);
}
