package com.spring.cloud.demo.uc;

import com.spring.cloud.demo.common.EnableCommonWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main.
 *
 * @author luojm
 * @date 2020/6/1 13:27
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.spring.cloud.demo.sms")
@EnableCommonWeb
public class UcApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcApplication.class, args);
    }
}
