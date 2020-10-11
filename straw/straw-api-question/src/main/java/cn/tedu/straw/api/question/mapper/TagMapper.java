package cn.tedu.straw.api.question.mapper;

import cn.tedu.straw.commons.vo.TagVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    /**
     * 查询所有的标签数据
     */
    List<TagVO> findAll();
}
