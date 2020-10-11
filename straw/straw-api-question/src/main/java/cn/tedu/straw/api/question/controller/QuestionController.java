package cn.tedu.straw.api.question.controller;


import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.service.IQuestionService;
import cn.tedu.straw.commons.ex.*;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionMostHitsVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/v1/questions")
@Slf4j
public class QuestionController {

    @Autowired
    IQuestionService service;

    //http://localhost:8081/v1/questions/post?title=&content=
    @RequestMapping("/post")
    public R post(@Valid PostQuestionDTO postQuestionDTO,
                  BindingResult bindingResult,
                  @AuthenticationPrincipal LoginUserInfo loginUserInfo){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new IllegalParameterException(errorMessage);
        }
        log.debug("从Session中获取当前的用户信息:{}",loginUserInfo);
        Integer userId = loginUserInfo.getId();
        String userNickName = loginUserInfo.getNickname();
        log.debug(">>> Session中的用户Id={}",userId);
        log.debug(">>> Session中的用户昵称={}",userNickName);
        service.postQuestion(postQuestionDTO,userId,userNickName);
        return R.ok();
    }

    @Value("${project.fileupload.question-image.max-size}")
    long maxSize;
    @Value("${project.fileupload.question-image.content-types}")
    List<String> contentTypes;
    @Value("${project.fileupload.base-dir}")
    String baseDir;
    @Value("${project.resource-server-name}")
    String resourceServerName;

    @PostMapping("/image/upload")
    public R imageUpload(MultipartFile file){
        // 检查上传的图片是否为空
        if(file.isEmpty()){
            throw new FileEmptyException("上传失败!选择有效的图片文件!");
        }
        // 检查上传的图片大小是否超标
        if(file.getSize()>maxSize){
            throw new FileSizeException("上传失败!文件大小不允许超过"+maxSize/1024+"kb的图片文件!");
        }
        // 检查上传的图片类型是否超标
        if(!contentTypes.contains(file.getContentType())){
            throw new FileTypeException("上传失败!图片类型不支持,请选择"+contentTypes+"类型的图片");
        }
        // 确定上传的文件保存到哪个文件夹
        // 利用配置文件中的配置值，结合年月得到例如 E:/static-resource/2020/09 这样的文件夹
        // 2020/09 < DateTimeFormatter.of("yyyy/MM").format(LocalDate.now())
        // 并结合File对象的exists()判断文件夹是否存在，如果不存在，则通过mkdirs()创建
        String yearAndMonth = DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now());
        File parent = new File(baseDir,yearAndMonth);
        if(!parent.exists()){
            parent.mkdirs();
        }
        // 确定上传的文件使用什么文件名
        String filename = System.currentTimeMillis()+"-"+System.nanoTime();
        // 确定上传的文件使用什么扩展名
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(beginIndex);
        // 确定上传的文件使用什么文件全名
        String child = filename + suffix;
        // 创建上传的文件在保存时使用的File对象
        File dest = new File(parent,child);
        try {
            // -- 执行保存上传的文件
            file.transferTo(dest);
        } catch (IOException e) {
            // -- 抛出FileUploadIOException
            throw new FileUploadIOException("上传失败！上传过程中出现读写错误，请稍后再次尝试！");
        } catch (IllegalStateException e) {
            // -- 抛出FileStateException
            throw new FileStateException("上传失败！文件状态有误，请检查上传的文件是否有效！");
        }
        String imageUrl = resourceServerName+"/"+yearAndMonth+"/"+child;
        log.debug("上传后的图片路径:{}",imageUrl);
        //日志
        log.debug("上传成功！文件路径：{}", dest);
        // 返回，暂定为R.ok()
        return R.ok(imageUrl);
    }

    @GetMapping("/most-hits")
    public R<List<QuestionMostHitsVO>> getHotHitsQuestion(){
        return R.ok(service.getHotHitsQuestions());
    }

    @GetMapping("/my")
    public R<PageInfo<QuestionListItemVO>> getMyQuestions(Integer page,
        @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        if (page == null || page < 1) {
            page = 1;
        }
        Integer userId = loginUserInfo.getId();
        PageInfo<QuestionListItemVO> pageInfo = service.getMyQuestions(page, userId);
        return R.ok(pageInfo);
    }

    // http://localhost:8081/v1/questions/15
// http://localhost/api-question/v1/questions/15
    @GetMapping("/{id}")
    public R<QuestionDetailVO> showQuestionDetail(@PathVariable("id") Integer id) {
        return R.ok(service.getQuestionDetail(id));
    }
}
