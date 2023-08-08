package com.techelevator.dao;

import com.techelevator.model.Book;

public interface BookDao {
<<<<<<< HEAD
    Book getBookByIsbn(Book book); // /book/{isbn}
    Book createBook(Book book); // /book
=======
    Book getBookByIsbn(String isbn); // /book/{isbn}
    Book createBook(Book book); // /book


>>>>>>> main
}
