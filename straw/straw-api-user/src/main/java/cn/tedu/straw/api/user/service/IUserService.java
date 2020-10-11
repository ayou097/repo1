package cn.tedu.straw.api.user.service;

import cn.tedu.straw.api.user.dto.RegisterStudentDTO;
import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-10
 */
public interface IUserService extends IService<User> {
    /**
     * 学生注册
     *
     * @param registerStudentDTO 客户端提交的学生信息
     */
    void registerStudent(RegisterStudentDTO registerStudentDTO);

    /**
     * 查询老师的列表,用于下拉菜单
     * @return 老师的列表
     */
    List<TeacherSelectOptionVO> getTeacherList();

    /**
     * 演示使用Ribbon获取"热点问答列表"
     * @return
     */
    R getMostHitsQuestions();
}
