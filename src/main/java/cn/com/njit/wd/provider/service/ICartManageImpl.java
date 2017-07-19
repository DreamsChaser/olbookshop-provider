package cn.com.njit.wd.provider.service;

import cn.com.njit.wd.api.dto.CartDTO;
import cn.com.njit.wd.api.dto.CartResDTO;
import cn.com.njit.wd.api.service.ICartManage;
import cn.com.njit.wd.provider.domain.Cart;
import cn.com.njit.wd.provider.repository.CartRepository;
import cn.com.njit.wd.provider.util.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdi on 2017/5/4.
 */
@Service("iCartManageImpl")
public class ICartManageImpl implements ICartManage{

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartResDTO queryByUserId(CartDTO cartDTO) {
        CartResDTO resDTO = new CartResDTO();
        List<CartDTO> cartList = new ArrayList<CartDTO>();
        cartList = cartRepository.queryByUserId(cartDTO.getUserId());
        resDTO.setCartDTOS(cartList);
        return resDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addToCart(CartDTO cartDTO) {
        Cart cart = BeanMapper.map(cartDTO,Cart.class);
//        cartRepository.addToCart(cartDTO.getBookId(),cartDTO.getUserId(),cartDTO.getBookNum());
        cartRepository.save(cart);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCart(CartDTO cartDTO) {
        cartRepository.deleteByCartId(cartDTO.getCartId());
    }
}
