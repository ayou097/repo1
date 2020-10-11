package cn.tedu.straw.gateway.security;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.gateway.service.IPermissionService;
import cn.tedu.straw.gateway.service.IUserService;
import cn.tedu.straw.gateway.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserService userService;
    @Autowired
    IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userService.getUserInfo(username);
        //如果没有查询到用户信息,则返回null
        if (user == null) {
            return null;
        }
        //根据用户名查询用户的权限信息
        List<PermissionVO> permissions = permissionService.getUserPermissions(username);
        //基于以上权限列表创建GrantedAuthority集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (PermissionVO permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
        }
        //创建返回对象
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .disabled(user.getIsEnabled()==0)
//                .accountLocked(user.getIsLocked()==1)
//                .accountExpired(false)
//                .credentialsExpired(false)
//                .authorities(authorities)
//                .build();
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                user.getUsername(),
                user.getPassword(),
                user.getIsEnabled()==1,
                true,
                true,
                user.getIsLocked()==0,
                authorities
        );
        loginUserInfo.setId(user.getId());
        loginUserInfo.setNickname(user.getNickname());
        //返回用户信息
        return loginUserInfo;
    }
}
