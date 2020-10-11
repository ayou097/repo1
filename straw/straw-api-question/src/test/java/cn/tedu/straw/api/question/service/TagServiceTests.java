package cn.tedu.straw.api.question.service;

import cn.tedu.straw.commons.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagServiceTests {
    @Autowired
    ITagService service;
    @Test
    void getTagList(){
        List<TagVO> tags = service.getTagList();
        log.debug("标签数量:"+tags.size());
        for (TagVO tag : tags) {
            log.debug(">>>"+tag);
        }
    }
}
