package com.spring.cloud.demo.auth2.domain;

import lombok.Data;

/**
 * token的信息结构体.
 */
@Data
public class TokenInfo {
    /**
     * token类型: (api:0 user:1)
     */
    private Integer tokenType;
    /**
     * App 信息
     */
    private AppInfo appInfo;
    /**
     * 用户其他数据
     */
    private UserInfo userInfo;
}