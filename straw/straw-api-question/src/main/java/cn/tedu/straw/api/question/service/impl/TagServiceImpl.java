package cn.tedu.straw.api.question.service.impl;

import cn.tedu.straw.api.question.mapper.TagMapper;
import cn.tedu.straw.api.question.service.ITagService;
import cn.tedu.straw.commons.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    TagMapper mapper;

    @Override
    public List<TagVO> getTagList() {
        return mapper.findAll();
    }
}
