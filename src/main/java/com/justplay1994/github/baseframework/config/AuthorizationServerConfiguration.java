package com.justplay1994.github.baseframework.config;

import com.justplay1994.github.baseframework.service.impl.AuthenticationServiceImpl;
import com.justplay1994.github.baseframework.service.impl.ClientDetailsServiceImpl;
import com.justplay1994.github.baseframework.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Collection;

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
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("clientDetailsServiceImpl")
    private ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    DataSource dataSource;

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
        clients.withClientDetails((ClientDetailsService) clientDetailsService);
//        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(new JdbcTokenStore(dataSource));
//        endpoints.tokenServices(tokenServices);

//        endpoints.authenticationManager(JdbcClientDetailsService)
    }

}
