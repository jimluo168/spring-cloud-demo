package com.spring.cloud.demo.common.web.exception;

/**
 * 业务异常类.
 *
 * @author luojm
 * @date 2020/6/1 15:19
 */
public class BusinessException extends AbstractException {
    public BusinessException(int code, String message) {
        super(code, message);
    }
}
