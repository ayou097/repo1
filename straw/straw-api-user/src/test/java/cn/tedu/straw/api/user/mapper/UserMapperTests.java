package cn.tedu.straw.api.user.mapper;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void insert(){
        User user = new User();
        user.setUsername("王昭君");
        user.setPassword("1234");
        user.setPhone("18200182020");
        int rows = mapper.insert(user);
        System.out.println("rows="+rows);
    }

    @Test
    void deleteById(){
        Integer id = 21;
        int rows = mapper.deleteById(id);
        System.out.println("rows="+rows);
    }

    @Test
    void updateById(){
        User user = new User();
        user.setId(20);
        user.setPassword("12345");
        user.setGender(1);
        user.setClassId(998);
        int rows = mapper.updateById(user);
        System.out.println("rows="+rows);
    }

    @Test
    void selectById(){
        Integer id = 24;
        User user = mapper.selectById(id);
        System.out.println("user="+user);
    }

    @Test
    void findByUsername(){
        String username = "ayou";
        User user = mapper.findByUsername(username);
        System.out.println("user="+user);
    }

    @Test
    void findByPhone(){
        String phone = "18200182009";
        User user = mapper.findByPhone(phone);
        System.out.println("user="+user);
    }

    @Test
    void findTeachers(){
        List<TeacherSelectOptionVO> list = mapper.findTeachers();
        log.debug("老师数量:"+list.size());
        for (TeacherSelectOptionVO teacherSelectOptionVO : list) {
            log.debug("老师信息:"+teacherSelectOptionVO);
        }
    }
}
