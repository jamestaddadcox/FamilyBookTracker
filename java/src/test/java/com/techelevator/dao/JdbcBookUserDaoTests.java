package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.BookUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcBookUserDaoTests extends BaseDaoTests{

    protected static final BookUser BOOK_USER_1 = new BookUser(1, "9781234567890", 30, true, true, "Read aloud", false, 10);
    protected static final BookUser BOOK_USER_2 = new BookUser(2, "9780987654321", 40, true, true, "Read aloud", false, 50);
    protected static final BookUser BOOK_USER_3 = new BookUser(3, "9785432109876", 50, true, true, "Read aloud", false, 100);
    private JdbcBookUserDao sut;
    private JdbcFamilyDao familyDao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcBookUserDao(jdbcTemplate);
    }

    @Test
    public void getBookUserInfoByUserIdAndIsbn_return_correct_for_book_id_and_isbn() {
        BookUser bookUser1 = sut.getBookUserInfoByUserIdAndIsbn(1, "9781234567890");
        assertBookUserMatch(BOOK_USER_1, bookUser1);

        BookUser bookUser2 = sut.getBookUserInfoByUserIdAndIsbn(2, "9780987654321");
        assertBookUserMatch(BOOK_USER_2, bookUser2);

        BookUser bookUser3 = sut.getBookUserInfoByUserIdAndIsbn(3, "9785432109876");
        assertBookUserMatch(BOOK_USER_3, bookUser3);
    }

    public void getAllBookUserInfoByUserId_returns_list_of_all_book_user(){
        List<BookUser> bookUserListOne = sut.getAllBookUserInfoByUserId(1);
        Assert.assertEquals(1, bookUserListOne.size());
        assertBookUserMatch(BOOK_USER_1, bookUserListOne.get(0));

        List<BookUser> bookUserListTwo = sut.getAllBookUserInfoByUserId(2);
        Assert.assertEquals(1, bookUserListOne.size());
        assertBookUserMatch(BOOK_USER_2, bookUserListOne.get(1));

        List<BookUser> bookUserListThree = sut.getAllBookUserInfoByUserId(3);
        Assert.assertEquals(1, bookUserListOne.size());
        assertBookUserMatch(BOOK_USER_2, bookUserListOne.get(2));
    }

    @Test
    public void updated_bookUserInfo_has_expected_values_when_retrieved() {
        BookUser bookUserToUpdate = sut.getBookUserInfoByUserIdAndIsbn(1, "9781234567890");

        bookUserToUpdate.setMinutesRead(bookUserToUpdate.getMinutesRead() + 1);
        bookUserToUpdate.setReadOutLoudReader(false);
        bookUserToUpdate.setReadOutLoudListener(false);
        bookUserToUpdate.setNotes(bookUserToUpdate.getNotes() + "Hello World");
        bookUserToUpdate.setCompleted(true);
        bookUserToUpdate.setPagesRead(bookUserToUpdate.getPagesRead() + 10);

        BookUser updatedBookUser = sut.updateBookUserInfo(bookUserToUpdate);
        assertBookUserMatch(bookUserToUpdate, updatedBookUser);
    }

    @Test
    public void getAllBookUserInfoByFamilyId_returns_list_of_all_book_user_by_family() {

    }

    @Test
    public void deleted_book_by_isbn_can_no_longer_be_retrieved() {
    }

    @Test
    public void addBookToUserList_has_expected_values_when_retrieved() {
    }


    private void assertBookUserMatch(BookUser expected, BookUser actual) {
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getIsbn(), actual.getIsbn());
        Assert.assertEquals(expected.getMinutesRead(), actual.getMinutesRead());
        Assert.assertEquals(expected.isReadOutLoudReader(), actual.isReadOutLoudReader());
        Assert.assertEquals(expected.isReadOutLoudListener(), actual.isReadOutLoudListener());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
        Assert.assertEquals(expected.isCompleted(), actual.isCompleted());
        Assert.assertEquals(expected.getPagesRead(), actual.getPagesRead());
    }

}
