package com.spring.cloud.demo.common.web.feign;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * MyParam的参数的处理.
 *
 * @author luojm
 * @date 2020/6/2 20:08
 */
public class MyParamParameterProcessor implements AnnotatedParameterProcessor {
    private static final Class<MyParam> ANNOTATION = MyParam.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {
        int parameterIndex = context.getParameterIndex();
        Class<?> parameterType = method.getParameterTypes()[parameterIndex];
        MethodMetadata data = context.getMethodMetadata();

        MyParam requestParam = ANNOTATION.cast(annotation);

        if (data.queryMapIndex() == null) {
            data.queryMapIndex(parameterIndex);
            data.queryMapEncoded(requestParam.encoded());
        }else {

        }
        return true;

    }
}
