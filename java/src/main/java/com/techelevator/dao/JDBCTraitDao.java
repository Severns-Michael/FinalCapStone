package com.techelevator.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCTraitDao implements TraitDao{
    private final JdbcTemplate jdbcTemplate;
    public JDBCTraitDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Trait> listAllTraits() throws DaoException {
        List<Trait> traitList = new ArrayList<>();
        String sql = "select * from traits"; // edit later
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                traitList.add(mapRowToTrait(rs));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return traitList;
    }

    @Override
    public Trait getTraitById(int id) throws DaoException {
        Trait trait = new Trait();
        String sql = "select * from Traits where trait_id = ?"; // edit
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                trait = mapRowToTrait(rs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return trait;
    }


    // MApRow
    public Trait mapRowToTrait(SqlRowSet rs) {
        Trait trait = new Trait();
       trait.setTraitId(rs.getInt("trait_id"));
       trait.setTraitName(rs.getString("trait_name"));
       return trait;
    }
}
