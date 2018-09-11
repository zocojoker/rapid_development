package com.foresee.exception;


/**
 * 异常处理
 *
 * @author foresee
 * @creat 2018-04-08-14:24
 */
public class AdminException extends MyBaseException {
    public AdminException() {
        super();
    }

    public AdminException(String message) {
        super(message);
    }

    public AdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminException(Throwable cause) {
        super(cause);
    }

    protected AdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
