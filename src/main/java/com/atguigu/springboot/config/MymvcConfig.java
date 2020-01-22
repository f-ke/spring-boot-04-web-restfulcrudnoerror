package com.atguigu.springboot.config;

import com.atguigu.springboot.component.MyLocaleResolver;
import com.atguigu.springboot.controller.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

//扩赞
@Configuration
public class MymvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //游览器发送atguigu请求来到指定页面
        registry.addViewController("/atguigu").setViewName("login");
    }
    //所有的WEBMVCCONFIG都会一起起作用
    @Bean
    public WebMvcConfigurerAdapter webmvcconfigureAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("/dashboard");
            }

            @Override
            //注册拦截器
            public void addInterceptors(InterceptorRegistry registry) {
                //springboot 已经做好了静态资源映射不用处理
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login","/webjars/**","/asserts/**");

            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }
        };
        return adapter;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
