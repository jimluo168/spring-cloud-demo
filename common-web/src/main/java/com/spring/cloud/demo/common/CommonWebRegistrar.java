package com.spring.cloud.demo.common;

import com.spring.cloud.demo.common.web.exception.GlobalExceptionHandler;
import com.spring.cloud.demo.common.web.feign.FeignBasicAuthRequestInterceptor;
import com.spring.cloud.demo.common.web.feign.MyContract;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/1 14:46
 */
public class CommonWebRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition(GlobalExceptionHandler.class.getName(),
                BeanDefinitionBuilder.rootBeanDefinition(GlobalExceptionHandler.class).getBeanDefinition());

        registry.registerBeanDefinition(FeignBasicAuthRequestInterceptor.class.getName(),
                BeanDefinitionBuilder.rootBeanDefinition(FeignBasicAuthRequestInterceptor.class).getBeanDefinition());

        registry.registerBeanDefinition(MyContract.class.getName(),
                BeanDefinitionBuilder.rootBeanDefinition(MyContract.class).getBeanDefinition());
    }
}
