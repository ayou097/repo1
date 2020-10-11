package cn.tedu.straw.commons.ex;

public class AnswerNotFoundException extends ServiceException {
    public AnswerNotFoundException() {
    }

    public AnswerNotFoundException(String message) {
        super(message);
    }

    public AnswerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnswerNotFoundException(Throwable cause) {
        super(cause);
    }

    public AnswerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
