package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class QuestionMostHitsVO implements Serializable {
    private Integer id;
    private String title;
    private String status;
    private Integer hits;
}
