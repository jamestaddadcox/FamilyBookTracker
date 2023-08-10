package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.User;

import java.util.List;

public interface FamilyDao {
    Family getFamilyById(int id); // /family/{id}

    Family createFamily(String familyName); // /family

    List<User> getFamilyMembersByFamilyId(int id); // /family/{family_id}/user GET
}
