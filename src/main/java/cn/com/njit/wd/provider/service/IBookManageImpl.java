package cn.com.njit.wd.provider.service;

import cn.com.njit.wd.api.dto.BookDTO;
import cn.com.njit.wd.api.dto.BookResDTO;
import cn.com.njit.wd.api.service.IBookManage;
import cn.com.njit.wd.provider.domain.Book;
import cn.com.njit.wd.provider.repository.BookRepository;
import cn.com.njit.wd.provider.util.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdi on 2017/5/3.
 */
@Service("iBookManageImpl")
public class IBookManageImpl implements IBookManage {

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addBook(BookDTO bookDTO) {
        if (StringUtils.isEmpty(bookDTO.getBookSpePrice())){
            bookDTO.setBookSpePrice(bookDTO.getBookPrice());
        }
        Book book = BeanMapper.map(bookDTO,Book.class);
        bookRepository.save(book);
    }

    @Override
    public BookResDTO listAllBook(BookDTO bookDTO) {
//        Sort sort=new Sort(new Sort.Order(Sort.Direction.DESC,"bookId"));
        BookResDTO bookResDTO = new BookResDTO();
        Page<Book> page = bookRepository.findAll(new PageRequest(bookDTO.getCurPage()-1,bookDTO.getPageSize()));
        List<Book> bookList = page.getContent();
        bookResDTO.setBookDTOList(BeanMapper.mapList(bookList,BookDTO.class));
        bookResDTO.setCount(page.getTotalPages());
        return bookResDTO;
    }

    @Override
    public BookDTO getById(BookDTO bookDTO) {
        BookDTO dto = new BookDTO();
        if(!StringUtils.isEmpty(bookDTO.getBookId())) {
            Book book = bookRepository.findOne(bookDTO.getBookId());
            dto = BeanMapper.map(book,BookDTO.class);
        }
        return dto;
    }

    @Override
    public BookResDTO findByAct(BookDTO bookDTO) {
        List<Book> bookList = new ArrayList<Book>();
        BookResDTO resDTO = new BookResDTO();
        Page<Book> page = bookRepository.queryByBookAct(bookDTO.getBookAct(),new PageRequest(bookDTO.getCurPage()-1,bookDTO.getPageSize()));
        bookList = page.getContent();
        resDTO.setCount(page.getTotalPages());
        resDTO.setBookDTOList(BeanMapper.mapList(bookList,BookDTO.class));
        return resDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBook(BookDTO bookDTO) {
        bookRepository.updateBook(bookDTO.getBookName(),bookDTO.getBookPrice(),bookDTO.getBookNum(),
                bookDTO.getBookAct(),bookDTO.getBookImg(),bookDTO.getBookDesc(),
                bookDTO.getBookMoreDesc(),bookDTO.getBookSpePrice(),bookDTO.getBookId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void decodeNumById(BookDTO bookDTO) {
        bookRepository.decodeNumById(bookDTO.getBookNum(),bookDTO.getBookId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBook(BookDTO bookDTO) {
        bookRepository.delete(bookDTO.getBookId());
    }

    @Override
    public List<BookDTO> queryByName(BookDTO bookDTO) {
        String bookName = "%"+bookDTO.getBookName()+"%";
        List<Book> bookList = bookRepository.queryByCondition(bookName);
        return BeanMapper.mapList(bookList,BookDTO.class);
    }

//    private Specification<Book> getSpecification(final BookDTO bookDTO){
//        return new Specification<Book>() {
//            @Override
//            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Predicate predicate = cb.conjunction();
//                if(!StringUtils.isEmpty(bookDTO.getBookNum())){
//                    predicate.getExpressions().add(cb.equal(root.get("bookNum"),bookDTO.getBookNum()));
//                }
//                return predicate;
//            }
//        };
//    }
}
