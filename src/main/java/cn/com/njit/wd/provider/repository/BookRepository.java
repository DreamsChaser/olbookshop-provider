package cn.com.njit.wd.provider.repository;

import cn.com.njit.wd.provider.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wangdi on 2017/5/3.
 */
public interface BookRepository extends JpaRepository<Book,String>,JpaSpecificationExecutor<Book>{

    @Query("SELECT b from Book b where b.bookAct=:bookAct")
    Page<Book> queryByBookAct(@Param("bookAct")String bookAct ,Pageable pageable);

    @Modifying
    @Query("update Book b set b.bookNum =:bookNum where b.bookId=:bookId")
    void decodeNumById(@Param("bookNum")String bookNum,@Param("bookId")String bookId);

    @Modifying
    @Query("update Book b set b.bookName=?1,b.bookPrice=?2,b.bookNum=?3," +
            "b.bookAct=?4,b.bookImg=?5,b.bookDesc=?6," +
            "b.bookMoreDesc=?7,b.bookSpePrice=?8 where b.bookId=?9")
    void updateBook(String bookName,String bookPrice,String bookNum,String bookAct,
                    byte[] bookImg,String bookDesc,String bookMoreDesc,String bookSpePrice,
                    String bookId);

    @Query("SELECT b from Book b where b.bookName like :bookName")
    List<Book> queryByCondition(@Param("bookName") String bookName);
}
