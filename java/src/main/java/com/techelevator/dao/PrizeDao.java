package com.techelevator.dao;

import com.techelevator.model.Prize;

import java.util.List;

public interface PrizeDao {
    Prize getPrizeById(int prizeId); // /prize/{id}
    List<Prize> getPrizesByFamilyId(int familyId); // /prize/family/{id}
    List<Prize> getPrizesByWinnerUserId(int userId); // /prize/user/{id}
    Prize createPrize(Prize newPrize); // /prize POST
    boolean deletePrizeById(int prizeId); // /prize/{id} DELETE
    boolean editPrize(Prize updatedPrize); // /prize/{id} PUT
}
