package com.spring.cloud.demo.uc.api;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.uc.feign.UserApiFeignFallback;
import org.aspectj.weaver.ast.ITestVisitor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User.
 *
 * @author luojm
 * @date 2020/6/1 13:24
 */
@RequestMapping("/user")
@FeignClient(name = "uc-service", fallbackFactory = UserApiFeignFallback.class)
public interface UserApi {

    @GetMapping("/getAccount")
    Result<String> getAccount();
}
