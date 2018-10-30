package com.justplay1994.baseframework;

import com.justplay1994.github.baseframework.Oauth2ServerApplication;
import com.justplay1994.github.baseframework.dao.MyUserDetailsDao;
import com.justplay1994.github.baseframework.dao.entity.MyUserDetailsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package: com.justplay1994.baseframework
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/29 16:21
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/29 16:21
 * @Update_Description: huangzezhou 补充
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisConfigTest {

    @Autowired
    MyUserDetailsDao myUserDetailsDao;

    @Test
    public void mybatisTest(){
        MyUserDetailsEntity userDetails = myUserDetailsDao.loadUserByUsername("admin");
        System.out.println(userDetails);
    }

}
