package com.spring.cloud.demo.common.web.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/2 14:39
 */
@Configuration
public class FeignConfiguration {
//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverters;
//
//    @Bean
//    public Encoder feignEncoder() {
//        return new PageableQueryEncoder(new SpringEncoder(messageConverters));
//    }

    @Bean
    public Contract myFeignContract() throws NoSuchFieldException, IllegalAccessException {
        return new MyContract();
    }
}
