package ru.maxima.springmvc.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    private int personId;
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2,max = 50,message = "Название книги не может быть не менее 2 символов и не более 50 символов")
    private String titleOfTheBook;
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2,max = 50,message = "Имя автора не может быть не менее 2 символов и не более 50 символов")
    private String author;

    @Min(value = 1900,message = "Год издания книги должен быть больше 1900 года")
    private int yearOfPublication;

    public Book() {
    }

    public Book(int id, int personId, String titleOfTheBook, String author, int yearOfPublication) {
        this.id = id;
        this.personId = personId;
        this.titleOfTheBook = titleOfTheBook;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTitleOfTheBook() {
        return titleOfTheBook;
    }

    public void setTitleOfTheBook(String titleOfTheBook) {
        this.titleOfTheBook = titleOfTheBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", personId=" + personId +
                ", titleOfTheBook='" + titleOfTheBook + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication ;
    }
}
