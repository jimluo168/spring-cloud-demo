package com.spring.cloud.demo.common.web.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Feign拦截器.
 *
 * @author luojm
 * @date 2020/6/2 13:36
 */
@Slf4j
@Configurable
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.method().equals("GET") && requestTemplate.request().body() != null) {
            try {
                JsonNode jsonNode = objectMapper.readTree(requestTemplate.request().body());
                requestTemplate.body((byte[]) null, requestTemplate.requestCharset());
                Map<String, Collection<String>> queries = new HashMap<>();
                //feign 不支持 GET 方法传 POJO, json body转query
                buildQuery(jsonNode, "", queries);
                requestTemplate.queries(queries);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("feign apply error.", e);
            }
        }
    }

    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        if (!jsonNode.isContainerNode()) { //叶子节点
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.computeIfAbsent(path, k -> new ArrayList<>());
            values.add(jsonNode.asText());
            return;
        }
        if (jsonNode.isArray()) { //数组节点
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else { //根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}
