package com.justplay1994.github.baseframework.service.impl;

import com.justplay1994.github.baseframework.dao.MyUserDetailsDao;
import com.justplay1994.github.baseframework.dao.entity.MyUserDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Package: com.justplay1994.github.baseframework.service.impl
 * @Project: base-framework
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/22 14:21
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/22 14:21
 * @Update_Description: huangzezhou 补充
 **/
@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MyUserDetailsDao userDetailsDao;

    @Override
    public MyUserDetailsEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetailsEntity user = userDetailsDao.loadUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("username:"+username);
        return user;
    }
}
