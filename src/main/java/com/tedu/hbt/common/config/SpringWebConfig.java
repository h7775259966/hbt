package com.tedu.hbt.common.config;

import com.tedu.hbt.common.web.TimeAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {//web.xml

    //配置spring mvc 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeAccessInterceptor())
                .addPathPatterns("/add");
    }
}
