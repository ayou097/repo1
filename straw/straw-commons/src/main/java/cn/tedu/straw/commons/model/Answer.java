package cn.tedu.straw.commons.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("answer")
@Accessors(chain = true)
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 答案正文
     */
    @TableField("content")
    private String content;

    /**
     * 作者id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 作者昵称
     */
    @TableField("user_nick_name")
    private String userNickName;

    /**
     * 问题id
     */
    @TableField("question_id")
    private Integer questionId;

    /**
     * 是否已采纳，0-未采纳，1-已采纳
     */
    @TableField("is_accepted")
    private Integer isAccepted;

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
