package com.lizhi.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;

/**
 * @author 10133
 */
@Service
public class TokenStoreFactory {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 声明TokenStore实现
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
        //使用redis存储令牌
        return new RedisTokenStore(redisConnectionFactory);
    }
}
