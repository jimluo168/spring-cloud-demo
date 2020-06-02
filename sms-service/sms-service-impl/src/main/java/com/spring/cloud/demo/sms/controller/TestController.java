package com.spring.cloud.demo.sms.controller;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.common.web.exception.BusinessException;
import com.spring.cloud.demo.sms.api.TestApi;
import com.spring.cloud.demo.sms.vo.TestVo;
import com.spring.cloud.demo.sms.vo.TestVo2;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.cloud.demo.common.model.Result.ok;

/**
 * Test 的实现类.
 *
 * @author luojm
 * @date 2020/6/1 13:00
 */
@RestController
public class TestController implements TestApi {
    @Override
    public Result<String> sayHello(String name) {
//        return "Hello " + name;
        throw new BusinessException(1000, "exception " + name);
    }

    @Override
    public Result<TestVo> sayHello2(String name) {
        throw new BusinessException(1000, "exception " + name);
    }

    @Override
    public Result<TestVo> sayHello3(TestVo vo1, TestVo2 vo2) {
        return ok(new TestVo().setName(vo1.getName() + " " + vo2.getName2()));
    }
}
