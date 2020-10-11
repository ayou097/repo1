package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class AnswerListItemVO implements Serializable {
    private Integer id;
    private String content;
    private Integer userId;
    private String userNickName;
    private Integer isAccepted;
    private LocalDateTime gmtCreate;
    private List<CommentListItemVO> comments;

}
