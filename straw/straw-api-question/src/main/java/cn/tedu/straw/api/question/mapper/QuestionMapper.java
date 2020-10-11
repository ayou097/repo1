package cn.tedu.straw.api.question.mapper;


import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-16
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 查询热点问题列表
     * @return
     */
    List<QuestionMostHitsVO> findHostHitsList();

    /**
     * 查询某用户的问题列表
     * @param userId
     * @return
     */
    List<QuestionListItemVO> findByUserId(Integer userId);

    /**
     * 查询问题详情
     * @param id
     * @return
     */
    QuestionDetailVO findById(Integer id);
}
