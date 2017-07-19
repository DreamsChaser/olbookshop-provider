package cn.com.njit.bookshop.test.service;

import cn.com.njit.bookshop.test.DubboApplication;
import cn.com.njit.wd.api.dto.BookDTO;
import cn.com.njit.wd.api.dto.BookResDTO;
import cn.com.njit.wd.provider.service.IBookManageImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by wangdi on 2017/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DubboApplication.class)
public class IBookManageTest {

    @Autowired
    IBookManageImpl iBookManage;

    @Test
    public void findByActTest(){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setCurPage(1);
        bookDTO.setPageSize(2);
        bookDTO.setBookAct("03");
        BookResDTO resDTO =  iBookManage.findByAct(bookDTO);
    }
}
