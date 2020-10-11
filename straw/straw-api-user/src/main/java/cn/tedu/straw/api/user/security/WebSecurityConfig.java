package cn.tedu.straw.api.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//@Configuration
@Deprecated
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //@Override
    //处理登录验证和授权
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                // 配置第1个用户，将是管理员账号，具有4种权限
//                .withUser("root")
//                // 登录密码，以下密码值是基于BCrypt对1234加密得到的,需要使用BCryptPasswordEncodig
//                .password("{bcrypt}$2a$10$5ou0o6KEOiSfTInPJtN7K.nxtz1ZA1.VOwYApnTHpVDbaax//jdui")
//                //授权，权限标识可以使用字符串数组来表示，字符串都是自定义的
//                .authorities("a_delete","a_update","u_list","u_info")
//                //继续配置
//                .and()
//                // 配置第2个用户，将是普通用户的账号，只具有2种权限
//                .withUser("mike")
//                .password("{bcrypt}$2a$10$5ou0o6KEOiSfTInPJtN7K.nxtz1ZA1.VOwYApnTHpVDbaax//jdui")
//                .authorities("u_list","u_info");
//    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //配置具体权限
    protected void configure(HttpSecurity http) throws Exception {
        //配置验证授权
        http.authorizeRequests()
                .antMatchers("/test/admin/delete").hasAuthority("a_delete")
                .antMatchers("/test/admin/update").hasAuthority("a_update")
                .antMatchers("/test/user/list").hasAuthority("u_list")
                .antMatchers("/test/user/info").hasAuthority("u_info")
                .anyRequest().authenticated();
        //启用登录表单
        http.formLogin();
        //关闭跨域攻击
        http.csrf().disable();
    }
}
