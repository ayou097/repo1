package cn.tedu.straw.gateway.service;

import cn.tedu.straw.commons.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    IUserService service;

    @Test
    void getUserInfo(){
        String username = "18100181004";
        User user = service.getUserInfo(username);
        System.err.println("查询结果:"+user);
    }
}
