package com.justplay1994.github.baseframework.dao.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.Jackson2ArrayOrStringDeserializer;
import org.springframework.security.oauth2.provider.client.JacksonArrayOrStringDeserializer;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Package: com.justplay1994.github.baseframework.dao.entity
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/29 20:58
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/29 20:58
 * @Update_Description: huangzezhou 补充
 **/
public class MyBaseClientDetailsEntity implements ClientDetails {

    private String clientId;        //oauth client 编号

    private String clientSecret;    //oauth client 密码

    private Set<String> scope = Collections.emptySet(); //client授予user的权限。

    private Set<String> resourceIds = Collections.emptySet();   //资源。

    private Set<String> authorizedGrantTypes = Collections.emptySet();

    private Set<String> registeredRedirectUris;

    private Set<String> autoApproveScopes;      //自动审批的权限集合。

    private List<GrantedAuthority> authorities = Collections.emptyList();   //server授予client的权限

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();

    public MyBaseClientDetailsEntity() {
    }

    public MyBaseClientDetailsEntity(ClientDetails prototype) {
        this();
        setAccessTokenValiditySeconds(prototype.getAccessTokenValiditySeconds());
        setRefreshTokenValiditySeconds(prototype
                .getRefreshTokenValiditySeconds());
        setAuthorities(prototype.getAuthorities());
        setAuthorizedGrantTypes(prototype.getAuthorizedGrantTypes());
        setClientId(prototype.getClientId());
        setClientSecret(prototype.getClientSecret());
        setRegisteredRedirectUri(prototype.getRegisteredRedirectUri());
        setScope(prototype.getScope());
        setResourceIds(prototype.getResourceIds());
    }

    public MyBaseClientDetailsEntity(String clientId, String resourceIds,
                                     String scopes, String grantTypes, String authorities) {
        this(clientId, resourceIds, scopes, grantTypes, authorities, null);
    }

    public MyBaseClientDetailsEntity(String clientId, String resourceIds,
                             String scopes, String grantTypes, String authorities,
                             String redirectUris) {

        this.clientId = clientId;

        if (StringUtils.hasText(resourceIds)) {
            Set<String> resources = StringUtils
                    .commaDelimitedListToSet(resourceIds);
            if (!resources.isEmpty()) {
                this.resourceIds = resources;
            }
        }

        if (StringUtils.hasText(scopes)) {
            Set<String> scopeList = StringUtils.commaDelimitedListToSet(scopes);
            if (!scopeList.isEmpty()) {
                this.scope = scopeList;
            }
        }

        if (StringUtils.hasText(grantTypes)) {
            this.authorizedGrantTypes = StringUtils
                    .commaDelimitedListToSet(grantTypes);
        } else {
            this.authorizedGrantTypes = new HashSet<String>(Arrays.asList(
                    "authorization_code", "refresh_token"));
        }

        if (StringUtils.hasText(authorities)) {
            this.authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(authorities);
        }

        if (StringUtils.hasText(redirectUris)) {
            this.registeredRedirectUris = StringUtils
                    .commaDelimitedListToSet(redirectUris);
        }
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setAutoApproveScopes(Collection<String> autoApproveScopes) {
        this.autoApproveScopes = new HashSet<String>(autoApproveScopes);
    }

    /**
     * 自动审批权限，是否通过。审批通过返回true
     * @param scope
     * @return
     */
    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * 判断是否被授予scope权限（判断scope是否为空或null）
     * @return
     */
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Collection<String> scope) {
        this.scope = scope == null ? Collections.<String> emptySet()
                : new LinkedHashSet<String>(scope);
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Collection<String> resourceIds) {
        this.resourceIds = resourceIds == null ? Collections
                .<String> emptySet() : new LinkedHashSet<String>(resourceIds);
    }

    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Collection<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = new LinkedHashSet<String>(
                authorizedGrantTypes);
    }

    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris == null ? null
                : new LinkedHashSet<String>(registeredRedirectUris);
    }

    private List<String> getAuthoritiesAsStrings() {
        return new ArrayList<String>(
                AuthorityUtils.authorityListToSet(authorities));
    }

    private void setAuthoritiesAsStrings(Set<String> values) {
        setAuthorities(AuthorityUtils.createAuthorityList(values
                .toArray(new String[values.size()])));
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        this.authorities = new ArrayList<GrantedAuthority>(authorities);
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }


    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(
            Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

//    public void setAdditionalInformation(Map<String, ?> additionalInformation) {
//        this.additionalInformation = new LinkedHashMap<String, Object>(
//                additionalInformation);
//    }

    public Map<String, Object> getAdditionalInformation() {
        return Collections.unmodifiableMap(this.additionalInformation);
    }

    public void addAdditionalInformation(String key, Object value) {
        this.additionalInformation.put(key, value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((accessTokenValiditySeconds == null) ? 0
                : accessTokenValiditySeconds);
        result = prime
                * result
                + ((refreshTokenValiditySeconds == null) ? 0
                : refreshTokenValiditySeconds);
        result = prime * result
                + ((authorities == null) ? 0 : authorities.hashCode());
        result = prime
                * result
                + ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes
                .hashCode());
        result = prime * result
                + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result
                + ((clientSecret == null) ? 0 : clientSecret.hashCode());
        result = prime
                * result
                + ((registeredRedirectUris == null) ? 0
                : registeredRedirectUris.hashCode());
        result = prime * result
                + ((resourceIds == null) ? 0 : resourceIds.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyBaseClientDetailsEntity other = (MyBaseClientDetailsEntity) obj;
        if (accessTokenValiditySeconds == null) {
            if (other.accessTokenValiditySeconds != null)
                return false;
        } else if (!accessTokenValiditySeconds.equals(other.accessTokenValiditySeconds))
            return false;
        if (refreshTokenValiditySeconds == null) {
            if (other.refreshTokenValiditySeconds != null)
                return false;
        } else if (!refreshTokenValiditySeconds.equals(other.refreshTokenValiditySeconds))
            return false;
        if (authorities == null) {
            if (other.authorities != null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        if (authorizedGrantTypes == null) {
            if (other.authorizedGrantTypes != null)
                return false;
        } else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes))
            return false;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (clientSecret == null) {
            if (other.clientSecret != null)
                return false;
        } else if (!clientSecret.equals(other.clientSecret))
            return false;
        if (registeredRedirectUris == null) {
            if (other.registeredRedirectUris != null)
                return false;
        } else if (!registeredRedirectUris.equals(other.registeredRedirectUris))
            return false;
        if (resourceIds == null) {
            if (other.resourceIds != null)
                return false;
        } else if (!resourceIds.equals(other.resourceIds))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (additionalInformation == null) {
            if (other.additionalInformation != null)
                return false;
        } else if (!additionalInformation.equals(other.additionalInformation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BaseClientDetails [clientId=" + clientId + ", clientSecret="
                + clientSecret + ", scope=" + scope + ", resourceIds="
                + resourceIds + ", authorizedGrantTypes="
                + authorizedGrantTypes + ", registeredRedirectUris="
                + registeredRedirectUris + ", authorities=" + authorities
                + ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
                + ", refreshTokenValiditySeconds="
                + refreshTokenValiditySeconds + ", additionalInformation="
                + additionalInformation + "]";
    }


    //=============  generate getter and setter  ===================================

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<String> getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    public void setAutoApproveScopes(Set<String> autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
