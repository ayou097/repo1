package cn.tedu.straw.gateway.mapper;


import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据则返回null
     */
    User findByUsername(String username);


}
