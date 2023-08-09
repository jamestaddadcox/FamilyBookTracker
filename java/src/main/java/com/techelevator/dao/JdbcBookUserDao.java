package com.techelevator.dao;

import com.techelevator.model.BookUser;
import com.techelevator.security.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcBookUserDao implements BookUserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookUserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BookUser getBookUserInfoByUserIdAndIsbn(int userId, String isbn) {
        String sql = "SELECT * FROM book_user WHERE user_id = ? AND isbn = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, isbn);

            if (results.next()) {
                return mapBookUserFromRowSet(results);
            }

            return null;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public BookUser updateBookUserInfo(BookUser bookUser) {
        String sql = "UPDATE book_user " +
                "SET isbn = ?, minutes_read = ?, read_aloud_reader = ?, read_aloud_listen = ?, notes = ?, completed = ?, pages_read = ? " +
                "WHERE user_id = ? AND isbn = ?;";

        try {
            jdbcTemplate.update(sql, bookUser.getIsbn(), bookUser.getMinutesRead(), bookUser.isReadOutLoudReader(), bookUser.isReadOutLoudListener(), bookUser.getNotes(), bookUser.isCompleted(), bookUser.getUserId(), bookUser.getIsbn(), bookUser.getPagesRead());

            return getBookUserInfoByUserIdAndIsbn(bookUser.getUserId(), bookUser.getIsbn());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    @Override
    public List<BookUser> getAllBookUserInfoByUserId(int userId) {
        List<BookUser> bookUserList = new ArrayList<>();
        String sql = "SELECT * FROM book_user WHERE user_id = ?;";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

            while (result.next()) {
                BookUser b = mapBookUserFromRowSet(result);
                bookUserList.add(b);
            }
            return bookUserList;

        } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public List<BookUser> getAllBookUserInfoByFamilyId(int familyId) {
        List<BookUser> bookUserList = new ArrayList<>();
        String sql = "SELECT * FROM book_user " +
                     "JOIN users USING (user_id) " +
                     "WHERE family_id = ?;";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, familyId);

            while (result.next()) {
                BookUser b = mapBookUserFromRowSet(result);
                bookUserList.add(b);
            }
            return bookUserList;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }


    @Override
    public BookUser addBookToUserList(BookUser bookUser) {
        String sql = "INSERT INTO book_user (user_id, isbn, minutes_read, read_aloud_reader, read_aloud_listen, notes, completed, pages_read) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (user_id, isbn) DO NOTHING " +
                "RETURNING user_id, isbn";

        try {
            BookUser newBookUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookUser.class),
                    bookUser.getUserId(),
                    bookUser.getIsbn(),
                    bookUser.getMinutesRead(),
                    bookUser.isReadOutLoudReader(),
                    bookUser.isReadOutLoudListener(),
                    bookUser.getNotes(),
                    bookUser.isCompleted(),
                    bookUser.getPagesRead());
            return newBookUser;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }




    @Override
    public boolean deleteBookByIsbn(BookUser bookUser) {
        String sql = "DELETE FROM book_user WHERE isbn = ? AND user_id = ?;";
        try{
            int numberOfRows = jdbcTemplate.update(sql, bookUser.getIsbn(), bookUser.getUserId());
            return numberOfRows > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    private BookUser mapBookUserFromRowSet(SqlRowSet rs) {
        BookUser bookUser = new BookUser();

        bookUser.setUserId(rs.getInt("user_id"));
        bookUser.setIsbn(rs.getString("isbn"));
        bookUser.setMinutesRead(rs.getInt("minutes_read"));
        bookUser.setReadOutLoudListener(false); // need to check on these guys
        bookUser.setReadOutLoudReader(false);
        bookUser.setNotes(rs.getString("notes"));
        bookUser.setCompleted(false);
        bookUser.setPagesRead(rs.getInt("pages_read"));

        return bookUser;
    }
}
