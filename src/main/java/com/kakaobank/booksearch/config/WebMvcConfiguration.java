package com.kakaobank.booksearch.config;

import com.kakaobank.booksearch.web.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    private static final int SEVEN_DAYS_IN_SECONDS = 6000;

//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:webapp")
//                .setCachePeriod(SEVEN_DAYS_IN_SECONDS);
//        super.addResourceHandlers(registry);
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.html");
        registry.addViewController("/search").setViewName("forward:/search.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addAppInterceptors(registry);
    }

    private void addAppInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(httpHeaderInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/v2/api-docs",
//                        "/swagger-resources/**",
//                        "/swagger-ui.html",
//                        "/error",
//                        "/webjars/**",
//                        "/resources/**");

        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/signin",
                        "/"
                        );
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }
}


