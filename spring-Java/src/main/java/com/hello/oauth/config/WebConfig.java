package com.hello.oauth.config;

import com.hello.oauth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 漫舞枪神
 * @date: 2023/4/18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 自行注入一个PasswordEncoder
     * @return org.springframework.security.crypto.password.PasswordEncoder
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //return new BCryptPasswordEncoder(10);
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 自行注入一个UserDetailsService
     * 如果没有的话，在UserDetailsServiceAutoConfiguration中会默认注入一个包含User用户的InMemoryUserDetailsManager
     * 另外也可以采用修改configure(AuthenticationManagerBuilder auth)方法并注入authenticationManagerBean的方式
     * @return org.springframework.security.core.userdetails.UserDetailsService
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Bean
    public UserDetailsService userDetailsService() {

//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(
//                User.withUsername("admin").password("123").authorities("admin").build()
//        );
//        return userDetailsManager;
//        return new JdbcUserDetailsManager();
        return new UserDetailsServiceImpl();
    }
}
