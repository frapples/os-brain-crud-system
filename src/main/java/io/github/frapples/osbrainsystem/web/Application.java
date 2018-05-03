package io.github.frapples.osbrainsystem.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 该注解作用分析： https://www.jianshu.com/p/4e1cab2d8431
@SpringBootApplication(scanBasePackages = "io.github.frapples.osbrainsystem")
@MapperScan("io.github.frapples.osbrainsystem.dal.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
