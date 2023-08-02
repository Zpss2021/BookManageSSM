package info.zpss.mapper;

import info.zpss.entity.Book;
import info.zpss.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select("select * from book")
    List<Book> getBookList();

    @Insert("insert into book (title, `desc`, price) values (#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String description, @Param("price") Double price);

    @Delete("delete from book where id = #{bookId}")
    void deleteBook(Integer bookId);

    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "sid", property = "sid"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "time", property = "time"),
            @Result(column = "title", property = "bookName"),
            @Result(column = "name", property = "stuName")
    })
    @Select("""
            select * from borrow
                left join book_manage.student s on s.id = borrow.sid
                left join book_manage.book b on b.id = borrow.bid
            """)
    List<Borrow> getBorrowList();

    @Insert("insert into borrow(sid, bid, time) values (#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid") Integer stuId, @Param("bid") Integer bookId);

    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(Integer id);
}
