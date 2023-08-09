package com.techelevator.controller;

import com.techelevator.dao.JdbcPrizeDao;
import com.techelevator.model.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PrizeController {

    @Autowired
    private JdbcPrizeDao prizeDao;

    @RequestMapping(path = "/prize/{prizeId}", method = RequestMethod.GET)
    public Prize getPrizeById(@PathVariable int prizeId) {
        return prizeDao.getPrizeById(prizeId);
    }
    @RequestMapping(path = "/prize/family/{familyId}", method = RequestMethod.GET)
    public List<Prize> getPrizesByFamilyId(@PathVariable int familyId) {
        return prizeDao.getPrizesByFamilyId(familyId);
    }
    @RequestMapping(path = "/prize/user/{userId}", method = RequestMethod.GET)
    public List<Prize> getPrizesByWinnerUserId(@PathVariable int userId) {
        return prizeDao.getPrizesByWinnerUserId(userId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/prize", method = RequestMethod.POST)
    public Prize createPrize(@Valid @RequestBody Prize prize) {
        return prizeDao.createPrize(prize);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/prize/prizeId", method = RequestMethod.DELETE)
    public int deletePrizeById(@PathVariable int prizeId) {
        Prize prize = prizeDao.getPrizeById(prizeId);

        if(prize == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The prize with id " + prizeId + " does not exist.");
        }

        return prizeDao.deletePrizeById(prizeId);
    }
}
