package cn.tedu.straw.api.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class StrawApiUserApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DataSource dataSource;
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    void bcryptEncode(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String encodePassword = passwordEncoder.encode(rawPassword);
        System.err.println("encodePassword="+encodePassword);
    }

    @Test
    void bcryptMatches(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String encodePassword = "$2a$10$grwG/3VPKG561fuZGeAG1eo6ApmUtilw/yX2mvZCBFX4g.KX8ah7O";
        boolean result = passwordEncoder.matches(rawPassword,encodePassword);
        System.err.println("原文="+rawPassword);
        System.err.println("密文="+encodePassword);
        System.err.println("验证结果="+result);
    }
}
