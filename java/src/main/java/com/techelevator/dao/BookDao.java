package com.techelevator.dao;

import com.techelevator.model.Book;

public interface BookDao {
    Book getBookByIsbn(String isbn); // /book/{isbn}
    Book createBook(Book book); // /book


}
