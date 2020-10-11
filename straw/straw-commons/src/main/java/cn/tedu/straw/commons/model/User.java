package cn.tedu.straw.commons.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名，学生注册时，使用手机号码作为用户名
     */
    @TableField("username")
    private String username;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别，0-女，1-男
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField("day_of_birth")
    private LocalDate dayOfBirth;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 班级id
     */
    @TableField("class_id")
    private Integer classId;

    /**
     * 是否启用，0-禁用，1-启用
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * 是否已锁定，0-未锁定，1-已锁定
     */
    @TableField("is_locked")
    private Integer isLocked;

    /**
     * 账号类型
     */
    @TableField("account_type")
    private Integer accountType;

    /**
     * 自我简介
     */
    @TableField("self_introduction")
    private String selfIntroduction;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


}
