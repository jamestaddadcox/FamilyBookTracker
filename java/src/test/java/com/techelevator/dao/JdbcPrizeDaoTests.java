package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.Prize;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class JdbcPrizeDaoTests extends BaseDaoTests {

//    protected static final Prize PRIZE_1 = new Prize(1, 1, "prize1", "description1", "adult", "2023-01-01", "2023-10-20");
//    protected static final Prize PRIZE_2 = new Prize(2, 2, "prize2", "description1", "adult", "2023-01-01", "2023-10-20");
//    protected static final Prize PRIZE_3 = new Prize(3, 2, "prize3", "description1", "adult", "2023-01-01", "2023-10-20");
    private JdbcPrizeDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPrizeDao(jdbcTemplate);
    }

//    @Test
//    public void getTimesheetById_returns_correct_timesheet_for_id() {
//        Prize prizeOne = sut.getPrizeById(1);
//        assertPrizeMatch(PRIZE_1, prizeOne);
//
//        Prize prizeTwo = sut.getPrizeById(2);
//        assertPrizeMatch(PRIZE_2, prizeTwo);
//
//        Prize prizeThree = sut.getPrizeById(3);
//        assertPrizeMatch(PRIZE_3, prizeThree);
//    }
//
//    @Test
//    public void getPrizeById_returns_null_when_id_not_found() {
//        Prize prize = sut.getPrizeById(-1);
//        Assert.assertNull(prize);
//    }
//
//    @Test
//    public void getPrizesByFamilyId_returns_list_of_all_prizes_for_family() {
//        List<Prize> prizeListOne = sut.getPrizesByFamilyId(1);
//        Assert.assertEquals(1, prizeListOne.size());
//        assertPrizeMatch(PRIZE_1, prizeListOne.get(0));
//
//        List<Prize> prizeListTwo = sut.getPrizesByFamilyId(2);
//        Assert.assertEquals(2, prizeListTwo.size());
//        assertPrizeMatch(PRIZE_2, prizeListTwo.get(1));
//    }
//
//    @Test
//    public void getPrizesByWinnerUserId_returns_list_of_all_prizes_for_winner() {
//        User winner = new User();
//        List<Prize> prizeListOne = sut.getPrizesByWinnerUserId(winner.getUserId());
//        Assert.assertEquals(1, prizeListOne.size());
//        assertPrizeMatch(PRIZE_1, prizeListOne.get(0));
//
//        List<Prize> prizeListTwo = sut.getPrizesByWinnerUserId(winner.getUserId());
//        Assert.assertEquals(2, prizeListTwo.size());
//        assertPrizeMatch(PRIZE_2, prizeListOne.get(1));
//    }
//
//    @Test
//    public void created_prize_has_expected_values_when_retrieved() {
////        Prize testPrize = new Prize(0, 1, "Grogu", "Baby Yoda", "child", "2024-02-02", "2024-02-03");
//        Prize createPrize = sut.createPrize(testPrize);
//
//        int newPrizeId = createPrize.getPrizeId();
//        Assert.assertTrue(newPrizeId > 0);
//
//        Prize retrievePrize = sut.getPrizeById(newPrizeId);
//        assertPrizeMatch(createPrize, retrievePrize);
//    }
//
//    @Test
//    public void deleted_prize_can_no_longer_be_retrieved() {
//        Prize rowsAffected = sut.getPrizeById(1);
//        Assert.assertEquals(1, rowsAffected);
//
//        Prize prize2 = sut.getPrizeById(1);
//        Assert.assertNull(prize2);
//    }
//
//    @Test
//    public void edit_prize_has_expected_values_when_retrieved() {
//        Prize prizeToUpdate = sut.getPrizeById(1);
//
//        prizeToUpdate.setFamilyId(prizeToUpdate.getFamilyId() + 1);
//        prizeToUpdate.setName(prizeToUpdate.getName() + "Hello World");
//        prizeToUpdate.setDescription(prizeToUpdate.getDescription() + "Extra Stuff");
//        prizeToUpdate.setUserGroup(prizeToUpdate.getUserGroup() + "Test1");
//        prizeToUpdate.setStartDate(prizeToUpdate.getStartDate() + "Test2");
//        prizeToUpdate.setEndDate(prizeToUpdate.getEndDate() + "Test3");
//
//        boolean updatedPrize = sut.editPrize(prizeToUpdate);
//        assertPrizeMatch(prizeToUpdate, updatedPrize);
//    }
//
//        private void assertPrizeMatch(Prize expected, Prize actual) {
//        Assert.assertEquals(expected.getPrizeId(), actual.getPrizeId());
//        Assert.assertEquals(expected.getFamilyId(), actual.getFamilyId());
//        Assert.assertEquals(expected.getName(), actual.getName());
//        Assert.assertEquals(expected.getDescription(), actual.getDescription());
//        Assert.assertEquals(expected.getUserGroup(), actual.getUserGroup());
//        Assert.assertEquals(expected.getStartDate(), actual.getEndDate());
//    }
//}
