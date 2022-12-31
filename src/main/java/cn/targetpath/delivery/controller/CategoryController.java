package cn.targetpath.delivery.controller;

import cn.targetpath.delivery.common.R;
import cn.targetpath.delivery.entity.Category;
import cn.targetpath.delivery.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/26 18:10
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增
     *
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 分页信息
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 跟进id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){

        categoryService.removeById(id);
        return R.success("删除成功");
    }
}