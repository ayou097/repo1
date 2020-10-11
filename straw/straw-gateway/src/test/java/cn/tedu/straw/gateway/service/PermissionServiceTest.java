package cn.tedu.straw.gateway.service;

import cn.tedu.straw.gateway.vo.PermissionVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    IPermissionService service;

    @Test
    void getUserPermissions(){
        String username = "18100181004";
        List<PermissionVO> permissions = service.getUserPermissions(username);
        System.err.println("权限数量:"+permissions.size());
        for (PermissionVO permission : permissions) {
            System.err.println("权限:"+permission);
        }
    }
}
