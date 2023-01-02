package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.entity.User;
import cn.targetpath.delivery.mapper.UserMapper;
import cn.targetpath.delivery.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/12/14 20:33
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {
}
