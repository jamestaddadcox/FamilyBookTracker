package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.security.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcBookDao implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Book theBook = null;

        String sql = "SELECT isbn, title, author, book_description, format " +
                     "FROM book " +
                     "WHERE isbn = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, isbn);
            if (result.next()) {
                theBook = mapRowToBook(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return theBook;
    }

    @Override
    public Book createBook(Book book) {
        String sql = "INSERT INTO book (isbn, title, author, book_description, format)" +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (isbn) DO NOTHING" +
                "       RETURNING isbn;";

        try {
            String bookISBN = jdbcTemplate.queryForObject(sql, String.class, book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getFormat());

            return getBookByIsbn(bookISBN);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Book mapRowToBook(SqlRowSet rs) {
        Book book = new Book();

        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setDescription(rs.getString("book_description"));
        book.setFormat(rs.getString("format"));

        return book;
    }
}
