package cn.targetpath.delivery.service;

import cn.targetpath.delivery.dto.DishDto;
import cn.targetpath.delivery.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 20:40
 */
public interface DishService extends IService<Dish> {
    /**
     * 新增物品
     * @param dishDto
     */
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);

}
