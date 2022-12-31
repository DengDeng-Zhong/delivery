package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.entity.Dish;
import cn.targetpath.delivery.mapper.DishMapper;
import cn.targetpath.delivery.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 20:43
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
