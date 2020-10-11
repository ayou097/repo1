package cn.tedu.straw.gateway.service;

import cn.tedu.straw.gateway.vo.PermissionVO;

import java.util.List;

/**
 * 查询用户的权限信息
 */
public interface IPermissionService {
    List<PermissionVO> getUserPermissions(String username);
}
