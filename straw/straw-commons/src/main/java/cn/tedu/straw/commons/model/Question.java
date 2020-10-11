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
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("question")
@Accessors(chain = true)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 正文
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
     * 状态，0-未回复，1-未解决，2-已解决
     */
    @TableField("status")
    private Integer status;

    /**
     * 点击量
     */
    @TableField("hits")
    private Integer hits;

    /**
     * 是否标记为已删除，0-未删除，1-已删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 问题的各标签id
     */
    @TableField("tag_ids")
    private String tagIds;

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
