package com.spring.cloud.demo.common.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 输出结果数据结构.
 *
 * @author luojm
 * @date 2020/6/1 14:20
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private int code = 200;
    private boolean success = Boolean.TRUE;
    private T data;
    private String msg;

    public Result() {
    }

    private Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> failure(int value, String msg) {
        return new Result<>((T) null).setCode(value).setMsg(msg);
    }
}
