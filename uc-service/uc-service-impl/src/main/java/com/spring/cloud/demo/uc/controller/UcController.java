package com.spring.cloud.demo.uc.controller;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.sms.api.TestApi;
import com.spring.cloud.demo.sms.vo.TestVo;
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
        return rs;
    }
}
