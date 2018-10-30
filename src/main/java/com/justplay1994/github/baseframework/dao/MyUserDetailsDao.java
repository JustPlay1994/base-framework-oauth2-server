package com.justplay1994.github.baseframework.dao;

import com.justplay1994.github.baseframework.dao.entity.MyUserDetailsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.justplay1994.github.baseframework.dao
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/29 15:51
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/29 15:51
 * @Update_Description: huangzezhou 补充
 **/
@Mapper
public interface MyUserDetailsDao {

    public MyUserDetailsEntity loadUserByUsername(String username);
}
