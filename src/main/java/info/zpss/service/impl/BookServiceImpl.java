package info.zpss.service.impl;

import info.zpss.entity.Book;
import info.zpss.entity.Borrow;
import info.zpss.mapper.BookMapper;
import info.zpss.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Borrow> getBorrowList() {
        return bookMapper.getBorrowList();
    }

    @Override
    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }

    @Override
    public Map<Book, Boolean> getBookMap() {
        Set<Integer> borrowSet = bookMapper.getBorrowList()
                .stream()
                .map(Borrow::getBid)
                .collect(Collectors.toSet());
        return bookMapper.getBookList()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        book -> borrowSet.contains(book.getId())
                ));
    }

    @Override
    public List<Book> getActiveBookList() {
        return getBookMap().entrySet()
                .stream()
                .filter(e -> !e.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void addBorrow(Integer stuId, Integer bookId) {
        bookMapper.addBorrow(stuId, bookId);
    }

    @Override
    public void returnBook(Integer bookId) {
        bookMapper.deleteBorrow(bookId);
    }

    @Override
    public void addBook(String title, String desc, Double price) {
        bookMapper.addBook(title, desc, price);
    }

    @Override
    public void deleteBook(int bookId) {
        bookMapper.deleteBook(bookId);
    }
}
