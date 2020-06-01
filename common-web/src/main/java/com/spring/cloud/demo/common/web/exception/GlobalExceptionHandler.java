package com.spring.cloud.demo.common.web.exception;

import com.spring.cloud.demo.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 系统全局异常统一处理类.
 *
 * @author luojm
 * @date 2020/6/1 14:01
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handler(Throwable throwable) {
        log.error("global exception handler", throwable);
        if (throwable instanceof AbstractException) {
            AbstractException ex = (AbstractException) throwable;
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.failure(ex.getCode(), ex.getMessage()));
        }
        if (throwable instanceof HttpRequestMethodNotSupportedException
                || throwable instanceof ConstraintViolationException
                || throwable instanceof MethodArgumentNotValidException
                || throwable instanceof BindException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Result.failure(HttpStatus.BAD_REQUEST.value(), throwable.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器出错"));
    }
}
