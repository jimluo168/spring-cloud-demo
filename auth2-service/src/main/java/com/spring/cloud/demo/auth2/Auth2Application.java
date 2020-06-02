package com.spring.cloud.demo.auth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main.
 *
 * @author luojm
 * @date 2020/6/2 9:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Auth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth2Application.class, args);
    }
}
