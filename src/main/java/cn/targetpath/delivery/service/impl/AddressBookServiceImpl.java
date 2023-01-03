package cn.targetpath.delivery.service.impl;

import cn.targetpath.delivery.entity.AddressBook;
import cn.targetpath.delivery.mapper.AddressBookMapper;
import cn.targetpath.delivery.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/12/16 21:30
 */
@Service
@Slf4j
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService  {
}
