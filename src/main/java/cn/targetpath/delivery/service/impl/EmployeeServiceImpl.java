package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.entity.Employee;
import cn.targetpath.delivery.mapper.EmployeeMapper;
import cn.targetpath.delivery.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/20 10:37
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
