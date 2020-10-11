package cn.tedu.straw.api.question.service.impl;

import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.mapper.QuestionMapper;

import cn.tedu.straw.api.question.mapper.QuestionTagMapper;
import cn.tedu.straw.api.question.mapper.UserQuestionMapper;
import cn.tedu.straw.api.question.service.IQuestionService;
import cn.tedu.straw.api.question.util.RedisUtils;
import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.ex.QuestionNotFoundException;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.model.QuestionTag;
import cn.tedu.straw.commons.model.UserQuestion;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import cn.tedu.straw.commons.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-16
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionTagMapper questionTagMapper;
    @Autowired
    UserQuestionMapper userQuestionMapper;

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void postQuestion(PostQuestionDTO postQuestionDTO, Integer userId, String userNickName) {
        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();
        //处理客户端提交的tagIds
        String tagIdsString = Arrays.toString(postQuestionDTO.getTagIds());
        tagIdsString = tagIdsString.substring(1,tagIdsString.length()-1);
        // 创建Question对象
        Question question = new Question()
            // 补全Question对象中的属性值：title			< 参数
            .setTitle(postQuestionDTO.getTitle())
            // 补全Question对象中的属性值：content		< 参数
            .setContent(postQuestionDTO.getContent())
            // 补全Question对象中的属性值：userId			< 参数
            .setUserId(userId)
            // 补全Question对象中的属性值：userNickName	< 参数
            .setUserNickName(userNickName)
            // 补全Question对象中的属性值：status			< 0
            .setStatus(0)
            // 补全Question对象中的属性值：hits			< 0
            .setHits(0)
            // 补全Question对象中的属性值：isDelete		< 0
            .setIsDelete(0)
            // 补全Question对象中的属性值：tagIds			< 参数，Arrays.toString()
            .setTagIds(tagIdsString)
            // 补全Question对象中的属性值：gmtCreate		< 当前时间now
            .setGmtCreate(now)
            // 补全Question对象中的属性值：gmtModified	< 当前时间now
            .setGmtModified(now);
            // 调用questionMapper.insert(question)插入数据，并获取返回值
            int rows = questionMapper.insert(question);
            // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
        }


        // 遍历参数tagIds
        for (int i = 0; i < postQuestionDTO.getTagIds().length; i++) {
            // -- 创建QuestionTag对象
            QuestionTag questionTag = new QuestionTag()
                // -- 补全QuestionTag对象中的属性值：questionId	< question.getId()
                .setQuestionId(question.getId())
                // -- 补全QuestionTag对象中的属性值：tagId		< 在tagIds中被遍历到的元素
                .setTagId(postQuestionDTO.getTagIds()[i])
                // -- 补全QuestionTag对象中的属性值：gmtCreate	< 当前时间now
                .setGmtCreate(now)
                // -- 补全QuestionTag对象中的属性值：gmtModified	< 当前时间now
                .setGmtModified(now);
                // -- 调用questionTagMapper.insert(questionTag)插入数据，并获取返回值
                rows = questionTagMapper.insert(questionTag);
                // -- 判断返回值是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！处理问题的标签数据时出现未知错误，请联系系统管理员！");
            }
        }


        // 遍历参数teacherIds
        for (int i = 0; i < postQuestionDTO.getTeacherIds().length; i++) {
            // -- 创建UserQuestion对象
            UserQuestion userQuestion = new UserQuestion()
                // -- 补全UserQuestion对象中的属性值：userId		< 在teacherIds中被遍历到的元素
                .setUserId(postQuestionDTO.getTeacherIds()[i])
                // -- 补全UserQuestion对象中的属性值：questionId	< question.getId()
                .setQuestionId(question.getId())
                // -- 补全UserQuestion对象中的属性值：gmtCreate	< 当前时间now
                .setGmtCreate(now)
                // -- 补全UserQuestion对象中的属性值：gmtModified	< 当前时间now
                .setGmtModified(now);
                // -- 调用userQuestionMapper.insert(userQuestion)插入数据，并获取返回值
                rows = userQuestionMapper.insert(userQuestion);
                // -- 判断返回值是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！处理回答问题的老师的数据时出现未知错误，请联系系统管理员！");
            }
        }

    }

    @Override
    public List<QuestionMostHitsVO> getHotHitsQuestions() {
        return questionMapper.findHostHitsList();
    }

    @Value("${project.question.my.page-size}")
    Integer pageSize;
    @Autowired
    RedisUtils redisUtils;

    @Override
    public PageInfo<QuestionListItemVO> getMyQuestions(Integer pageNum, Integer userId) {
        // 确保pageNum有效
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }

        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 从数据库中查询数据
        List<QuestionListItemVO> questions = questionMapper.findByUserId(userId);

        // 遍历查询结果，逐一补充标签数据
        for (QuestionListItemVO question : questions) {
            String tagIdsString = question.getTagIds(); // 6, 10, 15
            String[] tagIdsArray = tagIdsString.split(", "); // {6, 10, 15}
            List<TagVO> tags = new ArrayList<>();
            for (int i = 0; i < tagIdsArray.length; i++) {
                String key = "tag" + tagIdsArray[i]; // tag6
                TagVO tag = (TagVO) redisUtils.get(key); // { id:6, name: 'WEB' }
                tags.add(tag);
            }
            question.setTags(tags);
        }

        // 响应分页数据
        return new PageInfo<>(questions);
    }

    @Override
    public QuestionDetailVO getQuestionDetail(Integer id) {
        //调用持久层
        QuestionDetailVO question = questionMapper.findById(id);
        if (question == null) {
            throw new QuestionNotFoundException("获取问题详情失败!数据不存在!");
        }

        //补全tags属性的值
        String tagIdsString = question.getTagIds(); // 6, 10, 15
        String[] tagIdsArray = tagIdsString.split(", "); // {6, 10, 15}
        List<TagVO> tags = new ArrayList<>();
        for (int i = 0; i < tagIdsArray.length; i++) {
            String key = "tag" + tagIdsArray[i]; // tag6
            TagVO tag = (TagVO) redisUtils.get(key); // { id:6, name: 'WEB' }
            tags.add(tag);
        }
        question.setTags(tags);
        //返回查询结果
        return question;
    }

}
