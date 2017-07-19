package cn.com.njit.wd.provider.repository;

import cn.com.njit.wd.api.dto.CartDTO;
import cn.com.njit.wd.provider.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangdi on 2017/5/4.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart,String>{

    @Query("select new cn.com.njit.wd.api.dto.CartDTO(c.cartId,c.bookId,b.bookName,b.bookSpePrice,c.bookNum*b.bookSpePrice,c.bookNum) from Cart c,Book b where c.bookId = b.bookId and c.userId =:userId ")
    List<CartDTO> queryByUserId(@Param("userId")String userId);

//    @Modifying
//    @Query(value = "INSERT INTO t_cart(BOOK_ID,USER_ID,BOOK_NUM) VALUES (?1,?2,?3)",nativeQuery = true)
//    void addToCart(String bookId,String userId,String bookNum);
    @Modifying
    @Query("delete from Cart c where c.cartId =:cartId")
    void deleteByCartId(@Param("cartId")String cartId);
}
