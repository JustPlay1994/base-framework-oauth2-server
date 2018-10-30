package com.justplay1994.github.baseframework.dao.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @Package: com.justplay1994.github.baseframework.dao.entity
 * @Project: base-framework
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/22 14:53
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/22 14:53
 * @Update_Description: huangzezhou 补充
 **/
public class MyUserDetailsEntity implements UserDetails {

    String username;
    String password;
    Set<SimpleGrantedAuthority> authorities;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean enabled;


    public MyUserDetailsEntity(){

    }

    public MyUserDetailsEntity(String username, String password){
        this.username = username;
        this.password = password;
    }

    public MyUserDetailsEntity(String username, String password, Set<SimpleGrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * 账号是否被锁
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonExpired;
    }

    /**
     * 判断用户凭证是否已经过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * 是否有效
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toString(){
        return "MyUserDetailsEntity: [username="+username+", password="+password+",authorities="+authorities+"," +
                "accountNonExpired="+accountNonExpired+", credentialsNonExpired="+credentialsNonExpired+"," +
                "enabled"+enabled+"]";
    }
}
