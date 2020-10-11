package cn.tedu.straw.api.question.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PostCommentDTO implements Serializable {
    private Integer answerId;
    private String content;
}
