package com.justplay1994.github.baseframework.config;

import com.justplay1994.github.baseframework.service.impl.AuthenticationServiceImpl;
import com.justplay1994.github.baseframework.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @Package: com.justplay1994.github.baseframework.config
 * @Project: base-framework
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/22 10:47
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/22 10:47
 * @Update_Description: huangzezhou 补充
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("USER");
//    }

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Autowired
    DataSource dataSource;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()//关闭csrf
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/index")
//                .permitAll()
                .httpBasic()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
        //swagger调试，开放权限
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/v2/api-docs");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/swagger-resources/**");

        //自定义登录接口
        web.ignoring().antMatchers("/tokenLogin");
//        web.ignoring().antMatchers("/**");

        //auth2接口
//        web.ignoring().antMatchers("/oauth/authorize");     //授权
//        web.ignoring().antMatchers("/oauth/token");         //令牌
//        web.ignoring().antMatchers("/oauth/confirm_access");    //验证授权
//        web.ignoring().antMatchers("/oauth/error");         //错误指向
//        web.ignoring().antMatchers("/oauth/check_token");   //由资源服务器用于解码访问令牌
//        web.ignoring().antMatchers("/oauth/token_key");     //果使用JWT令牌，则公开用于令牌验证的公钥

    }
}
