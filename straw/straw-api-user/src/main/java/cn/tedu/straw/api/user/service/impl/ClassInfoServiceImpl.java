package cn.tedu.straw.api.user.service.impl;

import cn.tedu.straw.api.user.mapper.ClassInfoMapper;
import cn.tedu.straw.commons.model.ClassInfo;
import cn.tedu.straw.api.user.service.IClassInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-09-10
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements IClassInfoService {

}
