package cn.tedu.straw.api.user.service.impl;

import cn.tedu.straw.api.user.dto.RegisterStudentDTO;
import cn.tedu.straw.api.user.mapper.ClassInfoMapper;
import cn.tedu.straw.commons.ex.*;
import cn.tedu.straw.commons.model.ClassInfo;
import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.api.user.mapper.UserMapper;
import cn.tedu.straw.api.user.service.IUserService;
import cn.tedu.straw.api.user.util.PasswordUtils;
import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassInfoMapper classInfoMapper;

    @Override
    public void registerStudent(RegisterStudentDTO registerStudentDTO) {
        // 从参数registStudentDTO中取出用户名
        String username = registerStudentDTO.getUsername();
        // 基于以上取出的用户名，调用userMapper.findByUsername()查询用户数据
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：根据用户名找到了用户数据，则用户名已经被注册，将抛出UsernameDuplicateException
            throw new UsernameDuplicateException("注册失败！用户名已经被注册！");
        }

        //判断手机号是否已经被注册
        String phone = registerStudentDTO.getPhone();
        result = userMapper.findByPhone(phone);
        if (result != null) {
            throw new PhoneDuplicateException("注册失败！手机号码已经被注册！");
        }

        // 从参数registerStudentDTO中取出邀请码
        String inviteCode = registerStudentDTO.getInviteCode();
        // 基于以上取出的邀请码，调用classInfoMapper.selectOne()方法，根据邀请码查询班级信息
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("invite_code",inviteCode);
        ClassInfo classInfo = classInfoMapper.selectOne(queryWrapper);
        // 判断查询结果是否为null
        if (classInfo == null) {
            // 是：根据邀请码没有找到班级信息，则邀请码错误，将抛出InviteCodeException
            throw new InviteCodeException("注册失败！邀请码错误！");
        }

        // 判断以上查询到的班级信息的isEnabled是否为0
        if(classInfo.getIsEnabled() == 0){
            // 是：班级已经被禁用，将不允许注册，将抛出ClassDisabledException
            throw new ClassDisabledException("注册失败！班级已经被禁用！");
        }

        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();
        // 创建User对象
        User user = new User();
        // 补全user对象中的属性值：username < 从参数中获取(手机号作为用户名)
        user.setUsername(registerStudentDTO.getPhone());
        // 补全user对象中的属性值：password < 从参数中获取原密码进行加密

        String rawPassword = registerStudentDTO.getPassword();
        String encodePassword = PasswordUtils.encode(rawPassword);
        user.setPassword(encodePassword);

        // 补全user对象中的属性值：nickname < 从参数中获取
        user.setNickname(registerStudentDTO.getNickname());
        // 补全user对象中的属性值：gender < 从参数中获取
        user.setGender(registerStudentDTO.getGender());
        // 补全user对象中的属性值：dayOfBirth < 从参数中获取
        user.setDayOfBirth(registerStudentDTO.getDayOfBirth());
        // 补全user对象中的属性值：phone < 从参数中获取
        user.setPhone(registerStudentDTO.getPhone());
        // 补全user对象中的属性值：classId < 从班级信息中获取
        user.setClassId(classInfo.getId());
        // 补全user对象中的属性值：isEnabled < 1
        user.setIsEnabled(1);
        // 补全user对象中的属性值：isLocked < 0
        user.setIsLocked(0);
        // 补全user对象中的属性值：accountType < 0(学生)
        user.setAccountType(0);
        // 补全user对象中的属性值：selfIntroduction < 从参数中获取
        user.setSelfIntroduction(registerStudentDTO.getSelfIntroduction());
        // 补全user对象中的属性值：gmt_create < now
        user.setGmtCreate(now);
        // 补全user对象中的属性值：gmt_modified < now
        user.setGmtModified(now);
        // 基于以上user对象调用userMapper.insert()执行插入用户数据，并获取返回值
        int rows = userMapper.insert(user);
        // 判断返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：受影响行数不是1，则插入用户数据失败，将抛出InsertException
            throw new InsertException("注册失败！服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public List<TeacherSelectOptionVO> getTeacherList() {
        return userMapper.findTeachers();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public R getMostHitsQuestions() {
        String url = "http://api-question/v1/questions/most-hits";
        // 通过RestTemplate对象实现远程调用
        // - 参数1：远程调用时的目标URL
        // - 参数2：远程调用后目标服务器响应的结果的类型，
        // - - 如果远程服务器响应的是List集合类型，则此次填写为例如 String[].class、User[].class 数组类型
        // - 参数3【可变参数】：远程调用时需要提供的参数值，
        // - - 当需要参数值时，在URL中使用{1}、{2}等占位符表示第1个、第2个参数，
        // - - 并在调用以下getForObject()方法时，从第3个参数开始，依次填写各参数值
        R result = restTemplate.getForObject(url,R.class);
        return result;
    }

}
