package cn.tedu.straw.gateway.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cn.tedu.straw.gateway.vo.PermissionVO;

import java.util.List;

@SpringBootTest
public class PermissionMapperTests {

    @Autowired
    PermissionMapper mapper;

    @Test
    void findByUsername(){
        String username = "18100181005";
        List<PermissionVO> permissions = mapper.findByUsername(username);
        System.err.println("权限数量:"+permissions.size());
        for (PermissionVO permission : permissions) {
            System.err.println("权限:"+permission);
        }
    }
}
