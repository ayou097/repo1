package cn.tedu.straw.api.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RegisterStudentDTO implements Serializable {
    @NotBlank(message = "注册失败！邀请码错误！")
    private String inviteCode;
    @Pattern(regexp = "^\\d{11}$",message = "注册失败！手机号码格式错误！")
    private String phone;
    @NotBlank(message = "注册失败！请填写有效的昵称！")
    private String nickname;
    @Pattern(regexp = "^\\w{4,16}$",message = "注册失败！密码长度必须是4~16位")
    private String password;

    private String username;
    private Integer gender;
    private LocalDate dayOfBirth;
    private String selfIntroduction;
}
