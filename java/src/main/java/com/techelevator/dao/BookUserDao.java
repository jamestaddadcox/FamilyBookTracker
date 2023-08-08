package com.techelevator.dao;

import com.techelevator.model.BookUser;

import java.util.List;

public interface BookUserDao {
    BookUser getBookUserInfoByUserIdAndIsbn(int userId, String isbn); // /stats/user/{user_id}/book/{isbn}
    BookUser updateBookUserInfo(BookUser bookUser); // /stats/user/{id} PUT
    List<BookUser> getAllBookUserInfoByUserId(int userId); // /stats/user/{user_id}
    List<BookUser> getAllBookUserInfoByFamilyId(int familyId); // /stats/family/{family_id}
    BookUser addBookToUserList(BookUser bookUser);
    boolean deleteBookByIsbn(BookUser bookUser);


}

