package com.hello.weekly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param http org.springframework.security.config.annotation.web.builders.HttpSecurity
     * @throws Exception
     *
     * @author: 漫舞枪神
     * @date: 2023/4/119
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .hasAnyAuthority("admin")//这里采用了注解的方法级权限配置
                .anyRequest().authenticated();
                //.anyRequest().permitAll();
    }
}
