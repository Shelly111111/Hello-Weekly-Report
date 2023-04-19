package com.hello.weekly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     *
     * @param resources org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
     * @throws Exception
     *
     * @author: 漫舞枪神
     * @date: 2023/4/19
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.resourceId("admin")//资源ID
                .tokenServices(tokenServices())//使用远程服务验证令牌服务
                .stateless(true);//无状态模式
    }

    /**
     *
     * @param http org.springframework.security.config.annotation.web.builders.HttpSecurity
     * @throws Exception
     *
     * @author: 漫舞枪神
     * @date: 2023/4/19
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//校验请求
                .antMatchers("/**")//路径匹配规则
                .access("#oauth2.hasScope('all')")//需要匹配scope
                .and().cors().and().csrf().disable()//关闭cors和csrf跨域检查
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 配置access_token远程验证策略
     * @return
     *
     * @author: 漫舞枪神
     * @date: 2023/4/19
     */
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        services.setClientId("client_1");
        services.setClientSecret("secret");
        return services;
    }
}
