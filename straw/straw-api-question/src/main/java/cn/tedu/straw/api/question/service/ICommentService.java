package cn.tedu.straw.api.question.service;


import cn.tedu.straw.api.question.dto.PostCommentDTO;
import cn.tedu.straw.commons.model.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 发表评论
     * @param postCommentDTO
     * @param userId
     * @param userNickName
     */
    void post(PostCommentDTO postCommentDTO,Integer userId,String userNickName);
}
