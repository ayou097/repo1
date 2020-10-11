package cn.tedu.straw.gateway.service.impl;

import cn.tedu.straw.gateway.mapper.PermissionMapper;
import cn.tedu.straw.gateway.mapper.UserMapper;
import cn.tedu.straw.gateway.service.IPermissionService;
import cn.tedu.straw.gateway.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper mapper;

    @Override
    public List<PermissionVO> getUserPermissions(String username) {
        return mapper.findByUsername(username);
    }
}
