package com.spring.cloud.demo.common.web.feign;

import feign.Feign;
import feign.MethodMetadata;
import feign.Request;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URI;
import java.util.Map;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;
import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/2 15:59
 */
public class MyContract extends SpringMvcContract {
    private static final String ACCEPT = "Accept";

    private static final String CONTENT_TYPE = "Content-Type";

    private Map<String, Method> processedMethods2;
    private ResourceLoader resourceLoader2;
    private Map<Class<? extends Annotation>, AnnotatedParameterProcessor> annotatedArgumentProcessors2;

    public MyContract() throws NoSuchFieldException, IllegalAccessException {
        super();
        Field field = SpringMvcContract.class.getDeclaredField("processedMethods");
        field.setAccessible(true);
        processedMethods2 = (Map<String, Method>) field.get(this);

        Field field2 = SpringMvcContract.class.getDeclaredField("resourceLoader");
        field2.setAccessible(true);
        resourceLoader2 = (ResourceLoader) field2.get(this);

        Field field3 = SpringMvcContract.class.getDeclaredField("annotatedArgumentProcessors");
        field3.setAccessible(true);
        annotatedArgumentProcessors2 = (Map<Class<? extends Annotation>, AnnotatedParameterProcessor>) field3.get(this);
        MyParamParameterProcessor processor = new MyParamParameterProcessor();
        annotatedArgumentProcessors2.put(processor.getAnnotationType(), processor);
    }

    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
        super.processAnnotationOnMethod(data, methodAnnotation, method);
    }

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata data, Annotation[] annotations, int paramIndex) {
        boolean isHttpAnnotation = super.processAnnotationsOnParameter(data, annotations, paramIndex);

        Method method = this.processedMethods2.get(data.configKey());
        if (annotations != null && annotations.length > 0) {
            if (annotations[0] instanceof MyParam) {
                return true;
            }
        }
        return isHttpAnnotation;
    }

    @Override
    public MethodMetadata parseAndValidateMetadata(Class<?> targetType, Method method) {
        this.processedMethods2.put(Feign.configKey(targetType, method), method);
        MethodMetadata md = parseAndValidateMetadata2(targetType, method);

        RequestMapping classAnnotation = findMergedAnnotation(targetType,
                RequestMapping.class);
        if (classAnnotation != null) {
            // produces - use from class annotation only if method has not specified this
            if (!md.template().headers().containsKey(ACCEPT)) {
                parseProduces(md, method, classAnnotation);
            }

            // consumes -- use from class annotation only if method has not specified this
            if (!md.template().headers().containsKey(CONTENT_TYPE)) {
                parseConsumes(md, method, classAnnotation);
            }

            // headers -- class annotation is inherited to methods, always write these if
            // present
            parseHeaders(md, method, classAnnotation);
        }
        return md;
    }

    public MethodMetadata parseAndValidateMetadata2(Class<?> targetType, Method method) {
        String methodMetadaatClas = "feign.MethodMetadata";
        MethodMetadata data = null;
        try {
            Class<?> cls = Class.forName(methodMetadaatClas);
            Constructor<?> constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);
            data = (MethodMetadata) constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        data.targetType(targetType);
        data.method(method);

        Type returnType = invokeTypesResolve(targetType, targetType, method.getGenericReturnType());
        data.returnType(returnType);


        data.configKey(Feign.configKey(targetType, method));

        if (targetType.getInterfaces().length == 1) {
            processAnnotationOnClass(data, targetType.getInterfaces()[0]);
        }
        processAnnotationOnClass(data, targetType);


        for (Annotation methodAnnotation : method.getAnnotations()) {
            processAnnotationOnMethod(data, methodAnnotation, method);
        }
        if (data.isIgnored()) {
            return data;
        }
        checkState(data.template().method() != null,
                "Method %s not annotated with HTTP method type (ex. GET, POST)",
                data.configKey());
        Class<?>[] parameterTypes = method.getParameterTypes();
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int count = parameterAnnotations.length;
        for (int i = 0; i < count; i++) {
            boolean isHttpAnnotation = false;
            if (parameterAnnotations[i] != null) {
                isHttpAnnotation = processAnnotationsOnParameter(data, parameterAnnotations[i], i);
            }

            if (isHttpAnnotation) {
                if (!(parameterAnnotations[i][0] instanceof MyParam)) {
                    data.ignoreParamater(i);
                }
            }

            if (parameterTypes[i] == URI.class) {
                data.urlIndex(i);
            } else if (!isHttpAnnotation && parameterTypes[i] != Request.Options.class) {
                if (data.isAlreadyProcessed(i)) {
                    checkState(data.formParams().isEmpty() || data.bodyIndex() == null,
                            "Body parameters cannot be used with form parameters.");
                } else {
                    checkState(data.formParams().isEmpty(),
                            "Body parameters cannot be used with form parameters.");
                    checkState(data.bodyIndex() == null, "Method has too many Body parameters: %s", method);
                    data.bodyIndex(i);
                    data.bodyType(invokeTypesResolve(targetType, targetType, genericParameterTypes[i]));
                }
            }
        }

        if (data.headerMapIndex() != null) {
//            checkMapString("HeaderMap", parameterTypes[data.headerMapIndex()],
//                    genericParameterTypes[data.headerMapIndex()]);
        }

        if (data.queryMapIndex() != null) {
            if (Map.class.isAssignableFrom(parameterTypes[data.queryMapIndex()])) {
//                checkMapKeys("QueryMap", genericParameterTypes[data.queryMapIndex()]);
            }
        }

        return data;
    }

    private Type invokeTypesResolve(Type context, Class<?> contextRawType, Type toResolve) {
        try {
            Class<?> typeCls = Class.forName("feign.Types");
            Method typeMethod = typeCls.getDeclaredMethod("resolve", Type.class, Class.class, Type.class);
            typeMethod.setAccessible(true);
            Type returnType = (Type) typeMethod.invoke(null, context, contextRawType, toResolve);
            return returnType;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parseProduces(MethodMetadata md, Method method,
                               RequestMapping annotation) {
        String[] serverProduces = annotation.produces();
        String clientAccepts = serverProduces.length == 0 ? null
                : emptyToNull(serverProduces[0]);
        if (clientAccepts != null) {
            md.template().header(ACCEPT, clientAccepts);
        }
    }

    private void parseConsumes(MethodMetadata md, Method method,
                               RequestMapping annotation) {
        String[] serverConsumes = annotation.consumes();
        String clientProduces = serverConsumes.length == 0 ? null
                : emptyToNull(serverConsumes[0]);
        if (clientProduces != null) {
            md.template().header(CONTENT_TYPE, clientProduces);
        }
    }

    private void parseHeaders(MethodMetadata md, Method method,
                              RequestMapping annotation) {
        // TODO: only supports one header value per key
        if (annotation.headers() != null && annotation.headers().length > 0) {
            for (String header : annotation.headers()) {
                int index = header.indexOf('=');
                if (!header.contains("!=") && index >= 0) {
                    md.template().header(resolve(header.substring(0, index)),
                            resolve(header.substring(index + 1).trim()));
                }
            }
        }
    }

    private String resolve(String value) {
        if (StringUtils.hasText(value)
                && this.resourceLoader2 instanceof ConfigurableApplicationContext) {
            return ((ConfigurableApplicationContext) this.resourceLoader2).getEnvironment()
                    .resolvePlaceholders(value);
        }
        return value;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader2 = resourceLoader;
    }
}
