package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.common.CustomException;
import cn.targetpath.delivery.entity.Category;
import cn.targetpath.delivery.entity.Dish;
import cn.targetpath.delivery.entity.Setmeal;
import cn.targetpath.delivery.mapper.CategoryMapper;
import cn.targetpath.delivery.service.CategoryService;
import cn.targetpath.delivery.service.DishService;
import cn.targetpath.delivery.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 17:40
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId,id);

        int count = dishService.count(dishQueryWrapper);

        if (count >0){
           throw new CustomException("当前分类已关连其他物品,不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealQueryWrapper);
        if (count1 >0){
            throw new CustomException("当前分类已关连其他套餐,不能删除");
        }
        super.removeById(id);
    }
}
