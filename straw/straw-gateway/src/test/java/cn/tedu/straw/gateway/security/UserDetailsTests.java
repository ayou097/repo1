package cn.tedu.straw.gateway.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class UserDetailsTests {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername(){
        String username = "18100181003";
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.err.println("根据用户名"+username+"查询到的用户信息:");
        System.err.println(">>>"+userDetails);
    }
}
