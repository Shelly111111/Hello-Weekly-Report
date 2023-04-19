package com.hello.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 注入一个自定义的配置
 *
 * @author: 漫舞枪神
 * @date: 2023/4/18
 */
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//    @Autowired
//    private TokenStore tokenStore;

    /**
     * 配置安全拦截策略
     *
     * @param http org.springframework.security.config.annotation.web.builders.HttpSecurity
     * @throws Exception
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //链式配置拦截策略
        http.cors().and().csrf().disable()//关闭cors和csrf跨域检查
                //.logout().logoutUrl("/logout").and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()//所有人都能登录
                .antMatchers("/**").hasAnyAuthority("admin")//admin可以登录所有页面
//                .antMatchers("/revoke").permitAll()
//                .antMatchers("/login").permitAll()//所有人都能登录
                .anyRequest().authenticated();//其他请求需要登录
//                .and()
//                .rememberMe().userDetailsService(userDetailsService)//"on|yes|1|true"
//                .and()
//                .formLogin();
    }

    /**
     * 注入AuthenticationManager接口，启用OAuth2密码模式
     *
     * @return org.springframework.security.authentication.AuthenticationManager
     * @throws Exception
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }
}
