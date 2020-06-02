package com.spring.cloud.demo.uc.controller;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.sms.api.TestApi;
import com.spring.cloud.demo.sms.vo.TestVo;
import com.spring.cloud.demo.sms.vo.TestVo2;
import com.spring.cloud.demo.uc.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Uc.
 *
 * @author luojm
 * @date 2020/6/1 13:28
 */
@RestController
public class UcController implements UserApi {

    @Autowired
    private TestApi testApi;

    @Override
    public Result<String> getAccount() {
        Result<String> rs = testApi.sayHello("get account");
        Result<TestVo> rs2 = testApi.sayHello2("get account by testVo");
        TestVo vo1 = new TestVo();
        vo1.setName("vo1");
        TestVo2 vo2 = new TestVo2();
        vo2.setName2("vo2");
        Result<TestVo> rs3 = testApi.sayHello3(vo1, vo2);
        return rs;
    }
}
