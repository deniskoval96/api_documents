package com.hackathon.api.conf;

import com.hackathon.api.service.security.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.hackathon.api"})
public class ApiConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(new AuthenticationInterceptor())
        registry.addInterceptor(authenticationInterceptor)
                .excludePathPatterns("/users/login")
                .addPathPatterns("/**");

    }

}
