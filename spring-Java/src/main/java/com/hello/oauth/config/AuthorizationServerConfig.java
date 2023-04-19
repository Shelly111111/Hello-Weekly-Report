package com.hello.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenStore tokenStore;
    //会通过之前的ClientDetailsServiceConfigurer注入到Spring容器中
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * 配置客户端详情服务
     * @param clients org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
     * @throws Exception
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // client 密码
        //String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        //用于password认证
        clients.inMemory()
                //密码模式
                .withClient("client_1")//客户端ID
                .resourceIds("admin")//客户端拥有的资源列表
                .authorizedGrantTypes("password", "refresh_token")//认证范围，"authorization_code"、"password"、"client_credentials"、"implicit"、"refresh_token"
                .scopes("all")//允许授权的范围
//                .autoApprove(false)//跳转到授权页面
//                .authorities("admin")
                .secret("secret");
    }

    /**
     * 令牌管理服务
     * @return org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);//客户端详情服务
        services.setSupportRefreshToken(true);//允许令牌自动刷新
        services.setTokenStore(tokenStore);//令牌存储策略
        services.setRefreshTokenValiditySeconds(7200);//令牌默认有效期2小时
        services.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效期3天
        return services;
    }

    /**
     * 配置令牌访问端点和令牌服务
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .userDetailsService(userDetailsService)//用户管理
                .authenticationManager(authenticationManager)//启用oauth2管理
                .tokenServices(tokenServices())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);//接收GET和POST
    }

    /**
     * 配置令牌端点申请令牌的安全约束
     * @param oauthServer org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .checkTokenAccess("permitAll()")//oauth/check_token公开
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();//表单认证，申请令牌
    }

    /**
     * 设置授权码模式的授权码如何存取
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     *
     * @author: 漫舞枪神
     * @date: 2023/4/18
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
}
