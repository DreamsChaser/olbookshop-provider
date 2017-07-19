package cn.com.njit.wd.provider.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by wangdi on 2017/5/3.
 */
@Entity
@Table(name = "T_BOOK")
public class Book {

    @Column(name = "BOOK_ID")
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String bookId;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "BOOK_PRICE")
    private String bookPrice;

    @Column(name = "BOOK_SPE_PRICE")
    private String bookSpePrice;

    @Column(name = "BOOK_NUM")
    private String bookNum;

    @Column(name = "BOOK_ACT")
    private String bookAct;

    @Lob
    @Column(name = "BOOK_IMG" ,columnDefinition = "BLOB", nullable = false)
    private byte[] bookImg;

    @Column(name = "BOOK_DESC")
    private String bookDesc;

    @Column(name = "BOOK_MORE_DESC")
    private String bookMoreDesc;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookAct() {
        return bookAct;
    }

    public void setBookAct(String bookAct) {
        this.bookAct = bookAct;
    }

    public byte[] getBookImg() {
        return bookImg;
    }

    public void setBookImg(byte[] bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookMoreDesc() {
        return bookMoreDesc;
    }

    public void setBookMoreDesc(String bookMoreDesc) {
        this.bookMoreDesc = bookMoreDesc;
    }

    public String getBookSpePrice() {
        return bookSpePrice;
    }

    public void setBookSpePrice(String bookSpePrice) {
        this.bookSpePrice = bookSpePrice;
    }
}