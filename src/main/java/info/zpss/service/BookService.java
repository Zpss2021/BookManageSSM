package info.zpss.service;

import info.zpss.entity.Book;
import info.zpss.entity.Borrow;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();
    List<Book> getBookList();
    Map<Book, Boolean> getBookMap();
    List<Book> getActiveBookList();
    void addBorrow(Integer stuId, Integer bookId);
    void returnBook(Integer bookId);
    void addBook(String title, String desc, Double price);
    void deleteBook(int bookId);
}
