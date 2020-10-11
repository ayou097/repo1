package cn.tedu.straw.api.question.controller;


import cn.tedu.straw.api.question.dto.PostCommentDTO;
import cn.tedu.straw.api.question.service.ICommentService;
import cn.tedu.straw.commons.ex.IllegalParameterException;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    @Autowired
    ICommentService commentService;
    @RequestMapping("/post")
    public R<Void> post(@Valid PostCommentDTO postCommentDTO, BindingResult bindingResult,
                        @AuthenticationPrincipal LoginUserInfo loginUserInfo){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new IllegalParameterException(errorMessage);
        }
        commentService.post(postCommentDTO,loginUserInfo.getId(),loginUserInfo.getNickname());
        return R.ok();
    }
}
