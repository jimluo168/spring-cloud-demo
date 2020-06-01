package com.spring.cloud.demo.sms;

import com.spring.cloud.demo.common.EnableCommonWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Sms Main.
 *
 * @author luojm
 * @date 2020/6/1 12:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCommonWeb
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
