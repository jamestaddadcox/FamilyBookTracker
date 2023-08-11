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

    public JdbcPrizeDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Prize getPrizeById(int prizeId) {
        Prize prize = null;
        String sql = "SELECT prize_id, family_id, prize_name, prize_description, milestone, start_date, user_group, end_date FROM prize WHERE prize_id = ?;";
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
        String sql = "SELECT prize_id, family_id, prize_name, prize_description, milestone, start_date, user_group, end_date FROM prize WHERE family_id = ?";
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
        String sql = "SELECT prize_id, family_id, prize_name, prize_description, milestone, start_date, user_group, end_date FROM prize " +
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
        String sql = "INSERT INTO prize (family_id, prize_name, prize_description, milestone, start_date, user_group, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING prize_id";
        try {
            int newPrizeId = jdbcTemplate.queryForObject(sql, int.class, prize.getFamilyId(), prize.getName(),
                                prize.getDescription(), prize.isMilestone(), prize.getStartDate(), prize.getUserGroup(), prize.getEndDate());
            newPrize = getPrizeById(newPrizeId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newPrize;
    }

    @Override
    public int deletePrizeById(int prizeId) {
        String deletePrizeSql = "DELETE FROM prize WHERE prize_id = ?";
        try {

            int numberOfRows = jdbcTemplate.update(deletePrizeSql, prizeId);
            return numberOfRows;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public boolean editPrize(Prize updatedPrize) {
        String sql = "UPDATE prize SET family_id = ?, prize_name = ?, prize_description = ?, milestone = ?, " +
                     "user_group = ?, start_date = ?, end_date = ? WHERE prize_id ?";

        try {
            int numberOfRows = jdbcTemplate.update(sql,
                    updatedPrize.getFamilyId(),
                    updatedPrize.getName(),
                    updatedPrize.getDescription(),
                    updatedPrize.isMilestone(),
                    updatedPrize.getStartDate(),
                    updatedPrize.getEndDate(),
                    updatedPrize.getPrizeId(),
                    updatedPrize.getUserGroup()
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
        prize.setMilestone(false);
        prize.setUserGroup(rs.getString("user_group"));
        prize.setStartDate(rs.getDate("start_date"));
        prize.setEndDate(rs.getDate("end_date"));
        return prize;

    }
}
