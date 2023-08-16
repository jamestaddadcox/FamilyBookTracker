package com.techelevator.dao;

import com.techelevator.model.Prize;
import com.techelevator.security.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPrizeDao implements PrizeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Prize getPrizeById(int prizeId) {
        Prize prize = null;
        String sql = "SELECT prize_id, family_id, prize_name, prize_description, milestone, start_date, user_group, end_date, goal FROM prize WHERE prize_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, prizeId);
            if (results.next()) {
                prize = mapRowToPrize(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return prize;
    }

    @Override
    public List<Prize> getPrizesByFamilyId(int familyId) {
        List<Prize> prizes = new ArrayList<>();
        String sql = "SELECT prize_id, family_id, prize_name, prize_description, milestone, start_date, user_group, end_date, goal FROM prize WHERE family_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
            while (results.next()) {
                Prize prize = mapRowToPrize(results);
                prizes.add(prize);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } return prizes;
    }

    @Override
    public List<Prize> getPrizesByWinnerUserId(int userId) {
        List<Prize> prizes = new ArrayList<>();
        String sql = "SELECT prize_id, family_id, prize_name, prize_description, milestone, start_date, user_group, end_date, goal FROM prize " +
                     "JOIN prize_winner USING (prize_id) " +
                     "WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Prize prize = mapRowToPrize(results);
                prizes.add(prize);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } return prizes;
    }

    @Override
    public Prize createPrize(Prize prize) {
        Prize newPrize = null;
        String sql = "INSERT INTO prize (family_id, prize_name, prize_description, milestone, start_date, user_group, end_date, goal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING prize_id";
        try {
            int newPrizeId = jdbcTemplate.queryForObject(sql, int.class, prize.getFamilyId(), prize.getName(),
                                prize.getDescription(), prize.isMilestone(), prize.getStartDate(), prize.getUserGroup(), prize.getEndDate(), prize.getGoal());
            newPrize = getPrizeById(newPrizeId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw e;
        }

        return newPrize;
    }

    @Override
    public boolean deletePrizeById(int prizeId) {
        String deletePrizeSql = "DELETE FROM prize WHERE prize_id = ?";
        try {

            int numberOfRows = jdbcTemplate.update(deletePrizeSql, prizeId);
            return numberOfRows > 0;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public boolean editPrize(Prize updatedPrize, int prizeId) {
        String sql = "UPDATE prize SET family_id = ?, prize_name = ?, prize_description = ?, milestone = ?, " +
                "user_group = ?, start_date = ?, end_date = ?, goal = ? WHERE prize_id = ?";

        try {
            int numberOfRows = jdbcTemplate.update(sql,
                    updatedPrize.getFamilyId(),
                    updatedPrize.getName(),
                    updatedPrize.getDescription(),
                    updatedPrize.isMilestone(),
                    updatedPrize.getUserGroup(),
                    updatedPrize.getStartDate(),
                    updatedPrize.getEndDate(),
                    updatedPrize.getGoal(),
                    prizeId
            );
            return numberOfRows > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Prize mapRowToPrize(SqlRowSet rs) {
        Prize prize = new Prize();
        prize.setPrizeId(rs.getInt("prize_id"));
        prize.setFamilyId(rs.getInt("family_id"));
        prize.setName(rs.getString("prize_name"));
        prize.setDescription(rs.getString("prize_description"));
        prize.setMilestone(rs.getBoolean("milestone"));
        prize.setUserGroup(rs.getString("user_group"));
        prize.setStartDate(rs.getDate("start_date").toLocalDate());
        prize.setEndDate(rs.getDate("end_date").toLocalDate());
        prize.setGoal(rs.getInt("goal"));
        return prize;

    }
}
