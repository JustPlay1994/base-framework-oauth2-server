package com.justplay1994.github.baseframework.dao.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Package: com.justplay1994.github.baseframework.dao.model
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/24 19:34
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/24 19:34
 * @Update_Description: huangzezhou 补充
 **/
public class MyClientDetails implements ClientDetails {

    String clientId;
    Set<String> resourceIds;
    String clientSecret;
    Set<String> scope;
    Set<String> authorizedGrantTypes;
    Set<GrantedAuthority> authorities;


    public MyClientDetails(String clientId, String clientSecret, Set<GrantedAuthority> authorities, Set<String> authorizedGrantTypes){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorities = authorities;
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
