package cn.tedu.straw.api.question.controller;

import cn.tedu.straw.commons.ex.*;
import cn.tedu.straw.commons.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public R handleException(Throwable e) {
        if (e instanceof IllegalParameterException) {
            return R.failure(R.State.ERR_ILLEGAL_PARAMETER, e);
        } else if (e instanceof FileEmptyException) {
            return R.failure(R.State.ERR_FILE_EMPTY, e);
        } else if (e instanceof FileSizeException) {
            return R.failure(R.State.ERR_FILE_SIZE, e);
        } else if (e instanceof FileTypeException) {
            return R.failure(R.State.ERR_FILE_TYPE, e);
        } else if (e instanceof FileUploadIOException) {
            return R.failure(R.State.ERR_FILE_UPLOAD, e);
        } else if (e instanceof FileStateException) {
            return R.failure(R.State.ERR_FILE_STATE, e);
        } else if (e instanceof QuestionNotFoundException) {
            return R.failure(R.State.ERR_QUESTION_NOT_FOUND, e);
        } else if (e instanceof AnswerNotFoundException) {
            return R.failure(R.State.ERR_ANSWER_NOT_FOUND, e);
        }
        return R.failure(R.State.ERR_UNKNOWN, e);
    }
}