package cn.tedu.straw.api.question.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Data
@Accessors(chain = true)
public class PostQuestionDTO implements Serializable {

    @Size(min = 2,max = 100,message = "发布问题失败!标题的长度必须是2~100个字符之间!")
    private String title;
    @NotNull(message = "发布问题失败!请填写问题内容!")
    private String content;
    @NotNull(message = "发布问题失败!至少选择一个问题标签!")
    private Integer[] tagIds;
    @NotNull(message = "发布问题失败!至少选择一个老师!")
    private Integer[] teacherIds;
}
