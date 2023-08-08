package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.User;

import java.util.List;

public interface FamilyDao {
    Family getFamilyById(int id); // /family/{id}
    Family createFamily(Family family); // /family
    List<User> getFamilyMembersByFamilyId(int id); // /family/{family_id}/user GET
    boolean deleteFamily(int id); //family/{family_id}
}
