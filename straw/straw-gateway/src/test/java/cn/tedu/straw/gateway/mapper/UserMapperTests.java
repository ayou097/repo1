package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.commons.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    UserMapper mapper;
    @Test
    void findByUsername(){
        String username = "18100181005";
        User user = mapper.findByUsername(username);
        System.err.println(user);
    }
}
