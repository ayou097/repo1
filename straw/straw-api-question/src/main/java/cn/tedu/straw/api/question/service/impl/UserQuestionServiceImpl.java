package cn.tedu.straw.api.question.service.impl;

import cn.tedu.straw.api.question.mapper.UserQuestionMapper;
import cn.tedu.straw.commons.model.UserQuestion;
import cn.tedu.straw.api.question.service.IUserQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-16
 */
@Service
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionMapper, UserQuestion> implements IUserQuestionService {

}
