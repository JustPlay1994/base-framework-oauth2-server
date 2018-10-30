package com.justplay1994.github.baseframework.service.impl;

import com.justplay1994.github.baseframework.dao.entity.MyClientDetailsEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Package: com.justplay1994.github.baseframework.service.impl
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/24 19:16
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/24 19:16
 * @Update_Description: huangzezhou 补充
 **/
@Service("clientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        MyClientDetailsEntity clientDetails = null;
        if (clientId.equals("admin-oauth")) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("read");
            Set<GrantedAuthority> set = new HashSet<>();
            set.add(grantedAuthority);
            Set<String> authorizedGrantTypes = new HashSet<>();
            authorizedGrantTypes.add("authorization_code");
            authorizedGrantTypes.add("refresh_token");
            authorizedGrantTypes.add("password");
            clientDetails = new MyClientDetailsEntity("admin-oauth", "123-oauth", set, authorizedGrantTypes);
        }else {
            throw new NoSuchClientException("clientId="+clientId);
        }
        return clientDetails;
    }
}
