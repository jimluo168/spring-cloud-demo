package com.spring.cloud.demo.uc.feign;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.common.web.exception.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * Feign的fallback机制统一异常处理.
 *
 * @author luojm
 * @date 2020/6/1 15:56
 */
public class FeignFallbackUtil {
    public static <T> Result<T> fallback(Throwable throwable) {
        if (throwable instanceof HystrixTimeoutException) {
            return Result.failure(HttpStatus.BAD_GATEWAY.value(), "服务超时");
        }
        if (throwable instanceof AbstractException) {
            AbstractException ex = (AbstractException) throwable;
            return Result.failure(ex.getCode(), ex.getMessage());
        }
        return Result.failure(HttpStatus.SERVICE_UNAVAILABLE.value(), "uc-service服务不可用");
    }
}
