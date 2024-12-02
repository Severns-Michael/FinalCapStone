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

    @Override
    public Trait createTrait(Trait trait) throws DaoException {
        int traitsId;
        String sql = "insert into Traits (trait_name) values (?) RETURNING trait_id)"; //edit
        try {
            traitsId = jdbcTemplate.queryForObject(sql, int.class, trait.getTraitName());
            trait = getTraitById(traitsId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return trait;
    }

    @Override
    public Trait updateTrait(Trait trait) throws DaoException {
        String sql = "update trait set trait_name=? where trait_id = ?"; // edit
        try {
            int rows = jdbcTemplate.update(sql, trait.getTraitName());
            if (rows != 1) {
                throw new DaoException("Unable to update traits");
            }
            trait = getTraitById(trait.getTraitId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return trait;
    }

    @Override
    public void deleteTrait(int traitsId) throws DaoException {
        String sql = "delete from Traits where trait_id = ?"; //edit
        String sql2 = "delete from Breed_trait where trait_id = ?";
        try {
            jdbcTemplate.update(sql, traitsId);
            jdbcTemplate.update(sql2, traitsId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    // MApRow
    public Trait mapRowToTrait(SqlRowSet rs) {
        Trait trait = new Trait();
       trait.setTraitId(rs.getInt("trait_id"));
       trait.setTraitName(rs.getString("trait_name"));
       return trait;
    }
}
