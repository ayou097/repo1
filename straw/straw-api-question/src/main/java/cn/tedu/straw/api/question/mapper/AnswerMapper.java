package cn.tedu.straw.api.question.mapper;

import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.vo.AnswerListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
@Repository
public interface AnswerMapper extends BaseMapper<Answer> {

    /**
     *
     * @param questionId
     * @return
     */
    List<AnswerListItemVO> findByQuestionId(Integer questionId);
}
