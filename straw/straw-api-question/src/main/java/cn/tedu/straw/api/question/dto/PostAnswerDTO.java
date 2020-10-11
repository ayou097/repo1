package cn.tedu.straw.api.question.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class PostAnswerDTO implements Serializable {
    private Integer questionId;
    private String content;
}
