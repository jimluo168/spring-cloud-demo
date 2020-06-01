package com.spring.cloud.demo.common.web.exception;

/**
 * 抽象异常类.
 *
 * @author luojm
 * @date 2020/6/1 15:20
 */
public abstract class AbstractException extends RuntimeException {
    private int code;

    public AbstractException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
