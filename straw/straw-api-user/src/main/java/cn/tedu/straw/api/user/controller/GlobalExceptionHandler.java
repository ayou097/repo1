package cn.tedu.straw.api.user.controller;


import cn.tedu.straw.commons.ex.*;
import cn.tedu.straw.commons.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public R handleException(Throwable e) {
        if (e instanceof UsernameDuplicateException) {
           return R.failure(R.State.ERR_USERNAME_DUPLICATE,e);
        } else if (e instanceof InviteCodeException) {
            return R.failure(R.State.ERR_INVITE_CODE,e);
        } else if (e instanceof ClassDisabledException) {
            return R.failure(R.State.ERR_CLASS_DISABLED,e);
        } else if (e instanceof InsertException) {
            return R.failure(R.State.ERR_INSERT,e);
        } else if (e instanceof PhoneDuplicateException) {
            return R.failure(R.State.ERR_PHONE_DUPLICATE,e);
        } else if (e instanceof IllegalParameterException) {
            return R.failure(R.State.ERR_ILLEGAL_PARAMETER,e);
        } else {
            return R.failure(R.State.ERR_UNKNOWN,e);
        }
    }
}
