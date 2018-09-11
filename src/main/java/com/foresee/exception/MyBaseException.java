package com.foresee.exception;

/**
 * 自定义异常基类
 *
 * @author foresee
 * @creat 2018-04-08-9:41
 */
public class MyBaseException extends  RuntimeException {
    public MyBaseException() {
        super();
    }

    public MyBaseException(String message) {
        super(message);
    }

    public MyBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBaseException(Throwable cause) {
        super(cause);
    }

    protected MyBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
