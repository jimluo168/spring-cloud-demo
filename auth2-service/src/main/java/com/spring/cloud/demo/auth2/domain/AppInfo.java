package com.spring.cloud.demo.auth2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * appId 和 key 的结构体 appSecret.
 * AppID：应用的唯一标识
 * AppKey：公匙（相当于账号）
 * AppSecret：私匙（相当于密码）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
    /**
     * App id
     */
    private String appId;
    /**
     * API 秘钥
     */
    private String key;
}