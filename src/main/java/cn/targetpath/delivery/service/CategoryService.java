package cn.targetpath.delivery.service;

import cn.targetpath.delivery.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 17:38
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
