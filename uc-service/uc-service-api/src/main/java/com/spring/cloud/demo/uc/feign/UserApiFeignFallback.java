package com.spring.cloud.demo.uc.feign;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.sms.vo.TestVo;
import com.spring.cloud.demo.uc.api.UserApi;
import feign.hystrix.FallbackFactory;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/1 16:03
 */
public class UserApiFeignFallback implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable throwable) {
        return new UserApi() {
            @Override
            public Result<TestVo> getAccount() {
                return FeignFallbackUtil.fallback(throwable);
            }
        };
    }
}
