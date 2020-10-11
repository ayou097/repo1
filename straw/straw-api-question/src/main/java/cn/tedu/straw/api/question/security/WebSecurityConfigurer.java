package cn.tedu.straw.api.question.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //许可所有的请求
        http.authorizeRequests().anyRequest().permitAll();
        //禁用跨域攻击
        http.csrf().disable();
    }

}
