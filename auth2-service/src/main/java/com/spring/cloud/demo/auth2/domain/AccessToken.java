package com.spring.cloud.demo.auth2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * AccessToken的数据结构.
 */
@Data
@AllArgsConstructor
public class AccessToken {
    /**
     * token
     */
    private String token;


    /**
     * 失效时间
     */
    private Date expireTime;
}