package com.justplay1994.baseframework;

import com.justplay1994.github.baseframework.Oauth2ServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package: com.justplay1994.baseframework
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/30 11:31
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/30 11:31
 * @Update_Description: huangzezhou 补充
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcClientDetailsServiceTest {

    @Test
    public void test(){
        
    }

}
