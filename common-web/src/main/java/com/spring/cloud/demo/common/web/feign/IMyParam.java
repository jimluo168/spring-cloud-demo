package com.spring.cloud.demo.common.web.feign;

/**
 * MyParam的数据转换标识.
 *
 * @author luojm
 * @date 2020/6/3 10:01
 */
public interface IMyParam {

    default String toUrlParams() {
        return "IMyParam";
    }
}
