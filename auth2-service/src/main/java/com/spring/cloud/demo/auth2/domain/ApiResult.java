package com.spring.cloud.demo.auth2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {
    /**
     * 代码
     */
    private String code;
    /**
     * 结果
     */
    private String msg;
}