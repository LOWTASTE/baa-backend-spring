package com.lowt.baabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BAABackendApplication {

    @Bean
    public RestTemplate restTemplate() {     //在SpringBoot启动类中注册RestTemplate
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(BAABackendApplication.class, args);
    }
}