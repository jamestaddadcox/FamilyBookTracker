package com.techelevator.controller;
import com.techelevator.dao.BookDao;
import com.techelevator.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class BookController {

    private final BookDao bookDao;

@Autowired
    public BookController(BookDao bookdao) {
    this.bookDao = bookdao;
}
    @RequestMapping(path = "/book/{isbn}", method = RequestMethod.GET)
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookDao.getBookByIsbn(isbn);
    }

    @RequestMapping(path = "/book", method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        try {
            Book addedBook = bookDao.createBook(book);
            return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input or duplicate ISBN", e);
        }
    }



}


