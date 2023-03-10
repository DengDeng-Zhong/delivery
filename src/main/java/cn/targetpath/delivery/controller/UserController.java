package cn.targetpath.delivery.controller;

import cn.targetpath.delivery.common.R;
import cn.targetpath.delivery.entity.User;
import cn.targetpath.delivery.service.UserService;
import cn.targetpath.delivery.utils.SMSUtils;
import cn.targetpath.delivery.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/12/14 20:34
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 发送手机验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            // 生成随机验证码
            String vCode = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("vCode={}",vCode);
            // 调用短信服务API发送短信
            // SMSUtils.sendMessage("物流配送","",phone,vCode);

            // 将验证码保存到session中
            session.setAttribute(phone,vCode);
            return R.success("手机验证码发送成功");
        }
        return R.error("短信发送失败");
    }


    /**
     * 用户登陆
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        Object codeInSession = session.getAttribute(phone);

        if(codeInSession !=null && codeInSession.equals(code)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            session.setAttribute("user",user.getId());

            return R.success(user);
        }

        return R.error("登陆失败");
    }
}
