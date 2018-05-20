package io.github.frapples.osbrainsystem.web.config;

import io.github.frapples.osbrainsystem.web.resolver.UserIdArgumentResolver;
import java.util.List;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                argumentResolvers.add(new UserIdArgumentResolver());
            }
        };
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(10L* 1024L * 1024L);
        return factory.createMultipartConfig();
    }

    @Bean
    public FilterRegistrationBean regexpMatcherCorsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 允许 *.xxx.com 跨域
        String[] allowOrigins = new String[]{"*"};
        // String[] allowOrigins = new String[]{"https?://(.*).xxx.com(\\:\\d+)?"};
        registrationBean.setFilter(new RegexpMatcherCorsFilter(allowOrigins));
        return registrationBean;
    }
}
