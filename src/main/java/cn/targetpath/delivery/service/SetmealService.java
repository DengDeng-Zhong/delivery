package cn.targetpath.delivery.service;

import cn.targetpath.delivery.dto.SetmealDto;
import cn.targetpath.delivery.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 20:46
 */
public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
}
