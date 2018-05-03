package io.github.frapples.osbrainsystem.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 该注解作用分析： https://www.jianshu.com/p/4e1cab2d8431
@SpringBootApplication(scanBasePackages = "io.github.frapples.osbrainsystem")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
