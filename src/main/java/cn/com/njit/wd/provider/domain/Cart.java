package cn.com.njit.wd.provider.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by wangdi on 2017/5/4.
 */
@Entity
@Table(name = "T_CART")
public class Cart {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "CART_ID")
    private String cartId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "BOOK_ID")
    private String bookId;

//    @Column(name = "CART_PRICE")
//    private String cartPrice;

    @Column(name = "BOOK_NUM")
    private String bookNum;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }
}
