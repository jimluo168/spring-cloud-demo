package com.spring.cloud.demo.sms.feign;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.sms.api.TestApi;
import com.spring.cloud.demo.sms.vo.TestVo;
import feign.hystrix.FallbackFactory;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/1 13:47
 */
public class TestApiFeignFallback implements FallbackFactory<TestApi> {

    @Override
    public TestApi create(Throwable throwable) {
        return new TestApi() {
            @Override
            public Result<String> sayHello(String name) {
                return FeignFallbackUtil.fallback(throwable);
            }

            @Override
            public Result<TestVo> sayHello2(String name) {
                return FeignFallbackUtil.fallback(throwable);
            }
        };
    }
}
