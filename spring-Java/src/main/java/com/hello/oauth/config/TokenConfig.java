package com.hello.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 令牌存储策略
 *
 * @author 漫舞枪神
 * @date: 2023/4/18
 */
@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore() {
        //使用基于内存的普通令牌
        return new InMemoryTokenStore();
    }
}
