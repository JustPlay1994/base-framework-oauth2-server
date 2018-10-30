package com.justplay1994.baseframework;

import com.justplay1994.github.baseframework.Oauth2ServerApplication;
import com.justplay1994.github.baseframework.dao.BaseClientDetailsDao;
import com.justplay1994.github.baseframework.dao.entity.MyBaseClientDetailsEntity;
import com.justplay1994.github.baseframework.dao.entity.MyClientDetailsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Package: com.justplay1994.baseframework
 * @Project: oauth2-server
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/29 14:36
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/29 14:36
 * @Update_Description: huangzezhou 补充
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringOauthJdbcTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    BaseClientDetailsDao baseClientDetailsDao;

    @Test
    public void dataSourceTest() throws SQLException {
        System.out.println(dataSource
                .getConnection()
                .createStatement()
                .executeQuery("SELECT * FROM all_tab_columns"));
    }

    @Test
    public void jdbcClientTest() throws SQLException {

    }

    @Test
    public void baseClientDetailsDaoTest(){
        MyBaseClientDetailsEntity clientDetails = baseClientDetailsDao.loadClientByClientId("admin-oauth");
        System.out.println(clientDetails);
    }

}
