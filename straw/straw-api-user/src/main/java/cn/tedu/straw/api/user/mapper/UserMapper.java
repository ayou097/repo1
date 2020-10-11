package cn.tedu.straw.api.user.mapper;


import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    /**
     * 根据手机号码查询用户数据
     *
     * @param phone 手机号码
     * @return 匹配的用户数据，如果没有匹配的数据则返回null
     */
    User findByPhone(String phone);

    /**
     * 查询老师的列表,用于下拉菜单
     * @return 老师的列表
     */
    List<TeacherSelectOptionVO> findTeachers();
}
