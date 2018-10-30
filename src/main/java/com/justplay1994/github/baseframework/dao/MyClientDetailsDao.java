package com.justplay1994.github.baseframework.dao;

import com.justplay1994.github.baseframework.dao.entity.MyBaseClientDetailsEntity;
import com.justplay1994.github.baseframework.dao.entity.MyClientDetailsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Package: com.justplay1994.github.baseframework.dao.entity
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/29 19:09
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/29 19:09
 * @Update_Description: huangzezhou 补充
 **/
@Mapper
public interface MyClientDetailsDao {

    public MyBaseClientDetailsEntity loadClientByClientId(String clientId);

}
