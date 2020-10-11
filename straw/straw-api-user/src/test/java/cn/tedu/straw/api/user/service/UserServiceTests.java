package cn.tedu.straw.api.user.service;

import cn.tedu.straw.api.user.dto.RegisterStudentDTO;
import cn.tedu.straw.commons.ex.ServiceException;
import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserServiceTests {

    @Autowired
    IUserService service;

    @Test
    void registerStudent(){
        try{
            RegisterStudentDTO registerStudentDTO = new RegisterStudentDTO();
            registerStudentDTO.setInviteCode("JSD2005-666666");
            registerStudentDTO.setNickname("小安琪");;
            registerStudentDTO.setPhone("18300182201");
            registerStudentDTO.setPassword("1234");
            service.registerStudent(registerStudentDTO);
            System.err.println("注册成功！");
        } catch (ServiceException e){
            System.err.println("注册失败："+e.getClass().getName());
        }
    }

    @Test
    void getTeacherList(){
        List<TeacherSelectOptionVO> list = service.getTeacherList();
       log.debug("老师数量:"+list.size());
        for (TeacherSelectOptionVO teacherSelectOptionVO : list) {
            log.debug("老师信息:"+teacherSelectOptionVO);
        }
    }

    @Test
    void getMostHitsQuestions(){
        R r = service.getMostHitsQuestions();
        System.err.println(r);
    }
}
