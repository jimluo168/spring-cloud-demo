package com.spring.cloud.demo.common;

import com.spring.cloud.demo.common.web.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/1 14:46
 */
public class CommonWebRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(GlobalExceptionHandler.class);
        registry.registerBeanDefinition(GlobalExceptionHandler.class.getName(), bdb.getBeanDefinition());
    }
}
