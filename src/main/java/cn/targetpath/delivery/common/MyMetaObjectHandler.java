package cn.targetpath.delivery.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/12/30 15:20
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler  {
    @Override
    public void insertFill(MetaObject metaObject) {
      log.info("公共字段填充..insert.");

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("createUser",new Long(1));
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",new Long(1));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段填充..update.");
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",new Long(1));
    }
}
