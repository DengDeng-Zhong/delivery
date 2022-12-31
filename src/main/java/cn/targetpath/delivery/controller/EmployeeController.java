package cn.targetpath.delivery.controller;

import cn.targetpath.delivery.common.R;
import cn.targetpath.delivery.entity.Employee;
import cn.targetpath.delivery.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/20 10:40
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登陆
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        // 将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee one = employeeService.getOne(queryWrapper);

        //如果没有查询到则返回登录失败结果
        if (one == null) {
            return R.error("登录失败");
        }

        //密码比对,如果不一致则返回登录失败结果
        if (!one.getPassword().equals(password)) {
            return R.error("用户名或密码错误");
        }

        //查看账户状态,如果为已禁用状态,则返回用户已被禁用
        if (one.getStatus() == 0) {
            return R.error("用户已禁用");
        }

        //登录成功,将员工id存入session中并返回登录成功结果
        request.getSession().setAttribute("employee", one.getId());
        return R.success(one);
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //清理session 中保存的当前登录账号id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增账号
     *
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpServletRequest request) {

        employeeService.getById(employee.getId());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employeeService.save(employee);
        log.info("新增员工,员工信息{}", employee.toString());

        return R.success("账号添加成功");
    }

    /**
     * 账号信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={},pageSize={},name={}", page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        // 构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);

        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 更新账号状态
     *
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("employee {}", employee);

        long id = Thread.currentThread().getId();
        log.info("线程ID:{}", id);
//        Long empId = (Long)request.getSession().getAttribute("employee");
//
//        employee.setUpdateUser(empId);
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return R.success("更新成功");
    }

    /**
     * 根据ID查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        //当查询结果不为空返回查询信息
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("查询账号不存在");
    }

}
