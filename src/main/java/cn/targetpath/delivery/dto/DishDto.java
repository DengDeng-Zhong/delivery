package cn.targetpath.delivery.dto;


import cn.targetpath.delivery.entity.Dish;
import cn.targetpath.delivery.entity.DishFlavor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DengBo_Zhong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
