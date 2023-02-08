package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.model.Book;
import ru.maxima.springmvc.model.Person;

import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> findAllBooks(){
        return jdbcTemplate.query("select * from book order by book_id",new BookMapper());
    }
    public Book findOneBook(int id){
        return jdbcTemplate.query("select * from book where book_id = ?",new Object[]{id},new BookMapper()).
                stream().findAny().orElse(null);
    }
    public void saveBook(Book book){
        jdbcTemplate.update("insert into book (title_of_the_book,author,year_of_publication) values (?,?,?)",
                book.getTitleOfTheBook(),book.getAuthor(),book.getYearOfPublication());
    }
    public void updateBook(int id, Book updateBook){
        jdbcTemplate.update("update book set title_of_the_book = ?,author = ?, year_of_publication = ? where book_id = ?",
                updateBook.getTitleOfTheBook(),updateBook.getAuthor(),updateBook.getYearOfPublication(),id);
    }
    public void deleteBook(int id) {
        jdbcTemplate.update("delete from book where book_id = ?", id);
    }
    public void addBookInUser(Person person,int book_id) {
        jdbcTemplate.update("update book set person_id = ? where book_id = ?",
                person.getId(),book_id);
    }
}
