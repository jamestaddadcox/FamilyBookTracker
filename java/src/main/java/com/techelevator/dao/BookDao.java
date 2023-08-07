package com.techelevator.dao;

import com.techelevator.model.Book;

public interface BookDao {
    Book getBookByIsbn(Book book); // /book/{isbn}
    Book createBook(Book book); // /book
}
