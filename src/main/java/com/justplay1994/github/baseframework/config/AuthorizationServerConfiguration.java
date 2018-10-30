package com.justplay1994.github.baseframework.config;

import com.justplay1994.github.baseframework.service.impl.AuthenticationServiceImpl;
import com.justplay1994.github.baseframework.service.impl.ClientDetailsServiceImpl;
import com.justplay1994.github.baseframework.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.justplay1994.github.baseframework.config
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/24 17:02
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/24 17:02
 * @Update_Description: huangzezhou 补充
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    @Qualifier("clientDetailsServiceImpl")
//    private ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    AuthenticationServiceImpl authenticationService;    //oauth2 client，认证授权服务

    @Autowired
    UserDetailsServiceImpl userDetailsService;  //查找userDetail的服务

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;    //配置管理类

//    @Autowired
//    TokenStore tokenStore;

    @Autowired
    DataSource dataSource;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    DefaultTokenServices tokenServices;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()                  // 使用in-memory存储客户端信息
//                .withClient("admin-oauth")       // client_id
//                    .secret("123-oauth")                   // client_secret
//                    .authorizedGrantTypes("authorization_code", "refresh_token", "password")     // 该client允许的授权类型
//                    .scopes("read", "write")                     // 允许的授权范围
//                    .autoApprove(true);         //登录后绕过批准询问(/oauth/confirm_access)
//        clients.withClientDetails((ClientDetailsService) clientDetailsService);
        clients.jdbc(dataSource);
    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);


//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(new JdbcTokenStore(dataSource));
//        endpoints.tokenServices(tokenServices);
//        endpoints.authenticationManager(JdbcClientDetailsService)

        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .setClientDetailsService(clientDetailsService);
        //配置TokenServices参数
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); // 1天
        endpoints.tokenServices(tokenServices);

    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Bean(name = "tokenSource")
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    @Primary
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

}
