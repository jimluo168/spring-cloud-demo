package com.spring.cloud.demo.sms.vo;

import com.spring.cloud.demo.common.web.feign.IMyParam;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Test Vo.
 *
 * @author luojm
 * @date 2020/6/1 15:40
 */
@Data
@Accessors(chain = true)
public class TestVo implements IMyParam {
    private String name;
}
