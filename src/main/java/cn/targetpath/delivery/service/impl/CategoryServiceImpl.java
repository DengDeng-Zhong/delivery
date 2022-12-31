package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.entity.Category;
import cn.targetpath.delivery.mapper.CategoryMapper;
import cn.targetpath.delivery.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 17:40
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
