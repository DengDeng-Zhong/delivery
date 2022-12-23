package cn.targetpath.delivery.controller;

import cn.targetpath.delivery.common.R;
import cn.targetpath.delivery.entity.Employee;
import cn.targetpath.delivery.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        // 将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(queryWrapper);

        //如果没有查询到则返回登录失败结果
        if(one == null){
            return R.error("登录失败");
        }

        //密码比对,如果不一致则返回登录失败结果
        if(!one.getPassword().equals(password)){
            return R.error("用户名或密码错误");
        }

        //查看账户状态,如果为已禁用状态,则返回用户已被禁用
        if(one.getStatus() ==0){
            return R.error("用户已禁用");
        }

        //登录成功,将员工id存入session中并返回登录成功结果
        request.getSession().setAttribute("employee",one.getId());
        return R.success(one);
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session 中保存的当前登录账号id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}
