package com.justplay1994.github.baseframework.dao.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Package: com.justplay1994.github.baseframework.dao.entity
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/24 19:34
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/24 19:34
 * @Update_Description: huangzezhou 补充
 **/
public class MyClientDetailsEntity implements ClientDetails {

    String clientId;
    String clientSecret;
    Set<String> resourceIds;
    Set<String> scope;
    Set<String> authorizedGrantTypes;
    Set<GrantedAuthority> authorities;
    boolean secretRequired;
    Set<String> registeredRedirectUri;
    int accessTokenValiditySeconds;
    int refreshTokenValiditySeconds;
    boolean autoApprove;


    public MyClientDetailsEntity(String clientId, String clientSecret, Set<GrantedAuthority> authorities, Set<String> authorizedGrantTypes){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorities = authorities;
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    /**
     * client能访问的资源，为空则忽略。
     * @return
     */
    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    /**
     * 认证client是否需要密码
     * @return
     */
    @Override
    public boolean isSecretRequired() {
        return secretRequired;
    }


    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     *
     * client是否需要被限制在具体的scope中。如果为false（不需要），则scope的认证请求会被忽略。
     *
     * @return
     */
    @Override
    public boolean isScoped() {
        return false;
    }

    /**
     * 获取scope
     * @return
     */
    @Override
    public Set<String> getScope() {
        return scope;
    }

    /**
     * 授权的方式。常用的authorization_code、refresh_token、password
     * @return
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    /**
     * 返回授予oauth client的权限。不能为空。这个不同于userDetails中的权限，那个是授予用户的权限，这个是授予client的权限。
     * @return
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * token的有效时间，如果为null，则赋值一个默认值，30天。
     * @return
     */
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    /**
     * 刷新token的有效时间，不能为0或者负，null会有默认值。
     * @return
     */
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    /**
     * Test whether client needs user approval for a particular scope.
     * @param scope
     * @return
     */
    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    /**
     * 附加信息
     * @return
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
