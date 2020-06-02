package com.spring.cloud.demo.common.web.feign;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import static feign.Util.checkState;

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

        if (Map.class.isAssignableFrom(parameterType)) {
            checkState(data.queryMapIndex() == null,
                    "Query map can only be present once.");
            data.queryMapIndex(parameterIndex);

            return true;
        }

        MyParam requestParam = ANNOTATION.cast(annotation);

        Field[] fields = parameterType.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            context.setParameterName(name);
            Collection<String> query = context.setTemplateParameter(name + "." + name,
                    data.template().queries().get(name));
            data.template().query(name, query);
        }
        return true;
    }
}
