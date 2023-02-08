package ru.maxima.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springmvc.dao.BookDAO;
import ru.maxima.springmvc.dao.PersonDAO;
import ru.maxima.springmvc.model.Book;
import ru.maxima.springmvc.model.Person;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String findAllBooks(Model model){
        model.addAttribute("books",bookDAO.findAllBooks());
        return "books/index_book";
    }
    @GetMapping("/{id}")
    public String findOneBook(@PathVariable("id") int id, Model model){
        model.addAttribute("people",personDAO.findAllPersons());
        model.addAttribute("book",bookDAO.findOneBook(id));
        return "books/show_book";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/new_book";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "books/new_book";
        }

        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book",bookDAO.findOneBook(id));
        return "books/edit_book";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,@PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "books/edit_book";
        }
        bookDAO.updateBook(id,book);
        return "redirect:/books";
    }


    @PatchMapping("/select/{id}")
    public String addBookInUser(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        bookDAO.addBookInUser(person,id);
        return "redirect:/books/show_book";
    }


    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
