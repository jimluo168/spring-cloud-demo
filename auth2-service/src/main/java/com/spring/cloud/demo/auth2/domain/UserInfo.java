package com.spring.cloud.demo.auth2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
public class UserInfo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    /**
     * 盐
     */
    @JsonProperty(access = WRITE_ONLY)
    private String salt;


    private AccessToken accessToken;


    public UserInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
} 