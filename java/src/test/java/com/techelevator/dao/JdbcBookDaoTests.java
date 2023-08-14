package com.techelevator.dao;

import com.techelevator.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcBookDaoTests extends BaseDaoTests {

    protected static final Book BOOK_1 = new Book("9781234567890", "title1", "author1", "description1", "format1");
    protected static final Book BOOK_2 = new Book("9780987654321", "title2", "author2", "description2", "format2");
    protected static final Book BOOK_3 = new Book("9785432109876", "title3", "author3", "description3", "format3");
    private JdbcBookDao sut;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcBookDao(jdbcTemplate);
    }


    @Test
    public void GetBookByIsbn_returns_correct_book_for_isbn() {
        Book book1 = sut.getBookByIsbn("9781234567890");
        assertBookMatch(BOOK_1, book1);

        Book book2 = sut.getBookByIsbn("9780987654321");
        assertBookMatch(BOOK_2, book2);

        Book book3 = sut.getBookByIsbn("9785432109876");
        assertBookMatch(BOOK_3, book3);
    }

    @Test
    public void getBookByIsbn_returns_null_when_isbn_not_found() {
        Book book = sut.getBookByIsbn("");
        Assert.assertNull(book);
    }

    @Test
    public void created_book_has_expected_values_when_retrieved() {
        Book book = new Book("", "title4", "author4", "description4", "format4");
        Book createBook = sut.createBook(book);

        String newBookIsbn = createBook.getIsbn();
        Assert.assertEquals(newBookIsbn, book.getIsbn());

        Book retrieveBook = sut.getBookByIsbn(newBookIsbn);
        assertBookMatch(createBook, retrieveBook);
    }

    private void assertBookMatch(Book expected, Book actual) {
        Assert.assertEquals(expected.getIsbn(), actual.getIsbn());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getFormat(), actual.getFormat());
    }

}
