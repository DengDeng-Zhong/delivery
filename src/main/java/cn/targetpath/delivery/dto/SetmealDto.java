package cn.targetpath.delivery.dto;


import cn.targetpath.delivery.entity.Setmeal;
import cn.targetpath.delivery.entity.SetmealDish;
import lombok.Data;
import java.util.List;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/12/10 20:40
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
