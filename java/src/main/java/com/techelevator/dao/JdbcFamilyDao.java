package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.User;
import com.techelevator.security.exception.DaoException;
import org.checkerframework.checker.units.qual.C;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcFamilyDao implements FamilyDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFamilyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Family getFamilyById(int id) {
        Family family = null;
        String sql = "SELECT family_id, family_name FROM family WHERE family_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
            family = mapRowToFamily(results);
        }
    } catch(CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    }
    return family;
}

    @Override
    public Family createFamily(Family family) {
        Family newFamily = null;
        String insertFamilySql = "INSERT INTO family (family_name) values (?) RETURNING family_id";
        try {
            int newFamilyId = jdbcTemplate.queryForObject(insertFamilySql, int.class, family.getFamilyId());
            newFamily = getFamilyById(newFamilyId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newFamily;
    }

    @Override
    public List<User> getFamilyMembersByFamilyId(int id) {
        List<User> familyMembers = new ArrayList<>();
        String sql = "SELECT user_id, family_id, username, first_name, last_name, password_hash, activated, role, is_child FROM user WHERE family_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User familyMember = mapRowToUser(results);
                familyMembers.add(familyMember);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return familyMembers;
    }

    @Override
    public boolean deleteFamily(int id) {
        String deleteFamilySql = "DELETE FROM family WHERE family_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(deleteFamilySql, id);
            return numberOfRows > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Family mapRowToFamily(SqlRowSet rs) {
        Family family = new Family();
        family.setFamilyId(rs.getInt("family_id"));
        family.setFamilyName(rs.getString("family_name"));
        return family;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFamilyId(rs.getInt("family_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setChild(false);
        return user;
    }
}
