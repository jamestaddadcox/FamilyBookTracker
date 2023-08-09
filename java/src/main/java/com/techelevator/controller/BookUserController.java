package com.techelevator.controller;
import com.techelevator.dao.BookDao;
import com.techelevator.dao.BookUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.BookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookUserController {

    private final BookUserDao bookUserDao;
    private final BookDao bookDao;
    private final UserDao userDao;

    @Autowired
    public BookUserController(BookDao bookDao, UserDao userDao, BookUserDao bookUserDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
        this.bookUserDao = bookUserDao;
    }

    @RequestMapping(path = "/stats/user/{id}/book/{isbn}", method = RequestMethod.GET)
    public BookUser getBookUserInfoByUserIdAndIsbn(@PathVariable Integer id, @PathVariable String isbn) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is required >:(");
        }
        if (isbn == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Isbn is required >:(");
        }
        BookUser bookUser = bookUserDao.getBookUserInfoByUserIdAndIsbn(id, isbn);
        if (bookUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stats not found :(");
        } else {
            return bookUser;
        }
    }

    @RequestMapping(path = "/stats/user/{id}/book/{isbn}", method = RequestMethod.PUT)
    public BookUser updateBookUserInfo(@PathVariable Integer id, @PathVariable String isbn, @RequestBody BookUser bookUser) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is required");
        }
        if (isbn == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN is required");
        }
        BookUser existingUserInfo = bookUserDao.getBookUserInfoByUserIdAndIsbn(id, isbn);
        if (existingUserInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User information not found");
        } else {
            return bookUserDao.updateBookUserInfo(bookUser);
        }
    }
    @RequestMapping(path = "/stats/user/{id}", method = RequestMethod.GET)
    public List<BookUser> getAllBookUserInfoByUserId(@PathVariable Integer id) {
        return bookUserDao.getAllBookUserInfoByUserId(id);
    }

    @RequestMapping(path = "/stats/family/{id}", method = RequestMethod.GET)
    public List<BookUser> getAllBookUserInfoByFamilyId(@PathVariable Integer id) {
        return bookUserDao.getAllBookUserInfoByFamilyId(id);
    }

    @RequestMapping(path = "/stats", method = RequestMethod.POST)
    public ResponseEntity<BookUser> addBookToUserList(@RequestBody BookUser bookUser) {
        if (bookUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stat Not Found :(");
        } else {
            BookUser addedBookUser = bookUserDao.addBookToUserList(bookUser);
            return new ResponseEntity<>(addedBookUser, HttpStatus.CREATED);
        }
    }
}