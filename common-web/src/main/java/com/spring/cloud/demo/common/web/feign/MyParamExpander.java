package com.spring.cloud.demo.common.web.feign;

import feign.Param;

import java.util.Optional;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/3 10:00
 */
public class MyParamExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        if (value != null && value instanceof IMyParam) {
            return ((IMyParam) value).toUrlParams();
        }
        return Optional.ofNullable(value).orElse("").toString();
    }
}
