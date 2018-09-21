package com.zoco.exception;


/**
 * 异常处理
 *
 * @author zoco
 * @creat 2018-04-08-14:24
 */
public class RapidException extends BaseException {
    public RapidException() {
        super();
    }

    public RapidException(String message) {
        super(message);
    }

    public RapidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RapidException(Throwable cause) {
        super(cause);
    }

    protected RapidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
