package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.dto.SetmealDto;
import cn.targetpath.delivery.entity.Setmeal;
import cn.targetpath.delivery.entity.SetmealDish;
import cn.targetpath.delivery.mapper.SetmealMapper;
import cn.targetpath.delivery.service.SetmealDishService;
import cn.targetpath.delivery.service.SetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 20:45
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);

        List<SetmealDish> setmealDishes =  setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) ->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }
}
