package ru.maxima.springmvc.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.model.Book;
import ru.maxima.springmvc.model.Person;

import java.util.*;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person>  findAllPersons(){
        return jdbcTemplate.query("select * from person order by person_id",new PersonMapper());
    }
    public Person findOnePerson(int id){
        return jdbcTemplate.query("select * from person where person_id = ?",new Object[]{id},new PersonMapper()).
        stream().findAny().orElse(null);
    }
    public void savePerson(Person person){
        jdbcTemplate.update("insert into person(full_name,year_of_birth) values (?,?)",
                person.getFullName(),person.getYearOfBirth());
    }
    public void updatePerson(int id, Person updatePerson){
        jdbcTemplate.update("update person set full_name = ?,year_of_birth = ? where person_id = ?",
                updatePerson.getFullName(),updatePerson.getYearOfBirth(),id);
    }
    public void deletePerson(int id){
        jdbcTemplate.update("delete from person where person_id = ?",id);
    }

    public List<Book> findUserBooks(int id) {
        return jdbcTemplate.query("select * from book where person_id = ? order by book_id",new Object[]{id},new BookMapper());
    }

}
