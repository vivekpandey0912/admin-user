package com.vivek.bej.adminuser;

import com.vivek.bej.adminuser.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource("classpath:application-admin.yml")
public class AdminUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUserApplication.class, args);
    }



    @Bean
    public FilterRegistrationBean<JwtFilter> registerFilterBean() {
        FilterRegistrationBean<JwtFilter> jwtFilterBean = new FilterRegistrationBean<>();
        jwtFilterBean.setFilter(new JwtFilter());
        jwtFilterBean.addUrlPatterns("/adminbook/admin/*");
        return jwtFilterBean;
    }

}
