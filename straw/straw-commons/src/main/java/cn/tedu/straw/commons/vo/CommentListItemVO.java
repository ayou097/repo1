package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用于封装评论数据
 */
@Data
@Accessors(chain = true)
public class CommentListItemVO implements Serializable {
    private Integer id;
    private Integer userId;
    private String userNickName;
    private String content;
    private LocalDateTime gmtCreate;
}
