package cn.tedu.straw.api.question.service;

import cn.tedu.straw.commons.vo.TagVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ITagService {
    List<TagVO> getTagList();
}
