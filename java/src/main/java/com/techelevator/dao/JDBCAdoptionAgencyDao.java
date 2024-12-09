package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.AdoptionAgency;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCAdoptionAgencyDao implements AdoptionAgencyDao {

    private final JdbcTemplate jdbcTemplate;

    public JDBCAdoptionAgencyDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<AdoptionAgency> getAgencies() throws DaoException {
        List<AdoptionAgency> agencyList = new ArrayList<>();
        String sql = "select * from adoption_agency";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                agencyList.add(mapRowToAA(rs));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return agencyList;
    }

    @Override
    public AdoptionAgency getAgencyById(int agencyId) throws DaoException {
        AdoptionAgency agency = new AdoptionAgency();
        String sql = "select * from adoption_agency where agency_id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            if (rs.next()) {
                mapRowToAA(rs);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return agency;
    }

    public AdoptionAgency mapRowToAA(SqlRowSet rs) {
        AdoptionAgency newAA = new AdoptionAgency();
        newAA.setAgencyId(rs.getInt("agency_id"));
        newAA.setAgencyName(rs.getString("agency_name"));
        newAA.setAgencyCity(rs.getString("agency_city"));
        newAA.setAgencyState(rs.getString("agency_state"));
        newAA.setAgencyLocation();
        return newAA;
    }
}
