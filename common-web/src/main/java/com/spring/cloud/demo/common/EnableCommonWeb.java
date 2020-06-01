package com.spring.cloud.demo.common;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用该模块的自动配置.
 *
 * @author luojm
 * @date 2020/6/1 14:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CommonWebRegistrar.class)
public @interface EnableCommonWeb {
}
