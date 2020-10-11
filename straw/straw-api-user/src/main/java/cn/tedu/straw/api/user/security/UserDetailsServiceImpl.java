package cn.tedu.straw.api.user.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//@Component
@Deprecated
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //假设root是正确的用户名
        if("root".equals(username)){
            //用户名匹配的数据存在,则需要将该用户的详细数据信息返回到Spring Security,以完成密码验证及授权
            UserDetails userDetails = User.builder().username("root")
                    .password("{bcrypt}$2a$10$5ou0o6KEOiSfTInPJtN7K.nxtz1ZA1.VOwYApnTHpVDbaax//jdui")
                    .authorities("a_delete","a_update","u_list","u_info")
                    .build();
            return userDetails;
        }
        return null;
    }
}
