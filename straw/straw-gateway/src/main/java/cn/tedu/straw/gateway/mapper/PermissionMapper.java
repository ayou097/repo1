package cn.tedu.straw.gateway.mapper;

import org.springframework.stereotype.Repository;
import cn.tedu.straw.gateway.vo.PermissionVO;

import java.util.List;
@Repository
public interface PermissionMapper {
    /**
     * 查询用户的权限信息
     */
    List<PermissionVO> findByUsername(String username);
}
