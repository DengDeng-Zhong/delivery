package cn.targetpath.delivery.mapper;

import cn.targetpath.delivery.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号
 *
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/20 10:34
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
