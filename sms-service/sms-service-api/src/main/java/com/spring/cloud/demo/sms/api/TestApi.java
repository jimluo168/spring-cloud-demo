package com.spring.cloud.demo.sms.api;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.common.web.feign.MyParam;
import com.spring.cloud.demo.sms.feign.TestApiFeignFallback;
import com.spring.cloud.demo.sms.vo.TestVo;
import com.spring.cloud.demo.sms.vo.TestVo2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Test Api.
 *
 * @author luojm
 * @date 2020/6/1 12:50
 */
@RequestMapping("/test")
@FeignClient(name = "sms-service", fallbackFactory = TestApiFeignFallback.class)
public interface TestApi {

    @GetMapping("/sayHello")
    Result<String> sayHello(@RequestParam String name);

    @GetMapping("/sayHello2")
    Result<TestVo> sayHello2(@RequestParam String name);

    @GetMapping("/sayHello3")
    Result<TestVo> sayHello3(@MyParam TestVo vo1, @MyParam TestVo2 vo2);
}
