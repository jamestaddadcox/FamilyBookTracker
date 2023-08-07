package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.User;

import java.util.List;

public interface FamilyDao {
    Family getFamilyById(int id); // /family/{id}
    Family createFamily(Family family); // /family
    boolean addFamilyMember(User user); // /family/{family_id}/user/{userid} PUT ***Needs to trigger create user first then add family member
    boolean deleteFamilyMember(User user); // /family/user/{id}
    List<User> getFamilyMembersByFamilyId(int id); // /family/{family_id}/user GET
    boolean deleteFamily(Family family); //family/{family_id}
}
