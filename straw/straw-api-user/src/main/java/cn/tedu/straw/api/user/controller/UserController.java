package cn.tedu.straw.api.user.controller;


import cn.tedu.straw.api.user.dto.RegisterStudentDTO;
import cn.tedu.straw.api.user.service.IUserService;
import cn.tedu.straw.commons.ex.IllegalParameterException;
import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    IUserService userService;
    @RequestMapping("/reg")
    public R reg(@Valid RegisterStudentDTO registerStudentDTO, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           String errorMessage = bindingResult.getFieldError().getDefaultMessage();
//           System.err.println("验证请求参数出现错误：");
//           System.err.println(">>>"+errorMessage);
           throw new IllegalParameterException(errorMessage);
       }
       userService.registerStudent(registerStudentDTO);
       return R.ok();
    }

    @GetMapping("/teachers/select-option")
    public R<List<TeacherSelectOptionVO>> getTeacherList(){
        return R.ok(userService.getTeacherList());
    }

}
