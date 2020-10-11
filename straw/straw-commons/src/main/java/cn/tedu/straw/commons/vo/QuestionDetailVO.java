package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class QuestionDetailVO implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String userNickName;
    private Integer status;
    private Integer hits;
    private Integer isDelete;
    private String tagIds;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private List<TagVO> tags;

}
