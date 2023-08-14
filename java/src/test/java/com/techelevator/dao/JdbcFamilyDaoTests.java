package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcFamilyDaoTests extends BaseDaoTests{

    protected static final Family FAMILY_1 = new Family(1, "Johnson");
    protected static final Family FAMILY_2 = new Family(2, "Smith");
    protected static final Family FAMILY_3 = new Family(3, "Williams");
    private JdbcFamilyDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcFamilyDao(jdbcTemplate);
    }

    @Test
    public void getFamilyById_given_valid_user_id_returns_user() {
        Family family1 = sut.getFamilyById(FAMILY_1.getFamilyId());
        Assert.assertEquals(FAMILY_1, family1);

        Family family2 = sut.getFamilyById(FAMILY_2.getFamilyId());
        Assert.assertEquals(FAMILY_2, family2);

        Family family3 = sut.getFamilyById(FAMILY_3.getFamilyId());
        Assert.assertEquals(FAMILY_3, family3);
    }

    @Test
    public void created_family_has_expected_values_when_retrieved() {
        Family familyToCreate = new Family(0, "Robinson");
        Family createFamily = sut.createFamily(familyToCreate.getFamilyName());

        int newFamilyId = createFamily.getFamilyId();
        Assert.assertTrue(newFamilyId > 0);

        Family retrieveFamily = sut.getFamilyById(newFamilyId);
        assertFamilyMatch(createFamily, retrieveFamily);
    }

    @Test
    public void getFamilyMembersByFamilyId_returns_list_of_all_member_for_family() {
        List<User> familyListOne = sut.getFamilyMembersByFamilyId(1);
        Assert.assertEquals(1, familyListOne.size());
        assertFamilyMatch(FAMILY_1, familyListOne.get(0));


        List<User> familyListTwo = sut.getFamilyMembersByFamilyId(2);
        Assert.assertEquals(1, familyListOne.size());
        assertFamilyMatch(FAMILY_2, familyListTwo.get(1));

        List<User> familyListThree = sut.getFamilyMembersByFamilyId(3);
        Assert.assertEquals(1, familyListThree.size());
        assertFamilyMatch(FAMILY_3, familyListOne.get(2));
    }

    private void assertFamilyMatch(Family expected, Family actual) {
        Assert.assertEquals(expected.getFamilyId(), actual.getFamilyId());
        Assert.assertEquals(expected.getFamilyName(), actual.getFamilyName());
    }
}
