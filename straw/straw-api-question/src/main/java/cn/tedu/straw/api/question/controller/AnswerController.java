package cn.tedu.straw.api.question.controller;


import cn.tedu.straw.api.question.dto.PostAnswerDTO;
import cn.tedu.straw.api.question.service.IAnswerService;
import cn.tedu.straw.commons.ex.IllegalParameterException;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.AnswerListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/v1/answers")
@Slf4j
public class AnswerController {

    @Autowired
    IAnswerService answerService;
    @RequestMapping("/post")
    public R post(@Valid PostAnswerDTO postAnswerDTO, BindingResult bindingResult,
                  @AuthenticationPrincipal LoginUserInfo loginUserInfo){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new IllegalParameterException(errorMessage);
        }
        log.debug("从Session中获取当前用户的信息: {}",loginUserInfo);
        Integer userId = loginUserInfo.getId();
        String userNickName = loginUserInfo.getNickname();
        log.debug("Session中的用户ID= {}",userId);
        log.debug("Session中的用户昵称= {}",userNickName);
        answerService.post(postAnswerDTO,userId,userNickName);
        return R.ok();
    }

    @GetMapping("")
    public R<List<AnswerListItemVO>> getAnswerList(Integer questionId){
        return R.ok(answerService.getAnswerList(questionId));
    }
}
