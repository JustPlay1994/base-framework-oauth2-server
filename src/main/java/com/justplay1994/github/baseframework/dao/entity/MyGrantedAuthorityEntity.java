package com.justplay1994.github.baseframework.dao.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Package: com.justplay1994.github.baseframework.dao.entity
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/29 17:38
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/29 17:38
 * @Update_Description: huangzezhou 补充
 **/
public class MyGrantedAuthorityEntity implements GrantedAuthority {

    String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
