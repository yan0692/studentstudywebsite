package com.yan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Sam
 * @date 06/07/2020 - 1:09 pm
 */
@SpringBootApplication
@ComponentScan( basePackages = {"com.yan", "com.yan.interceptor", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
