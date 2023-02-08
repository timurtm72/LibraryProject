package ru.maxima.springmvc.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.springmvc.model.Book;
import ru.maxima.springmvc.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper  implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setPersonId(resultSet.getInt("person_id"));
        book.setTitleOfTheBook(resultSet.getString("title_of_the_book"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfPublication(resultSet.getInt("year_of_publication"));

        return book;
    }
}
