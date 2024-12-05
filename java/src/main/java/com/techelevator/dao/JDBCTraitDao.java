package com.techelevator.dao;
import com.techelevator.model.Breed;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCTraitDao implements TraitDao {
    private final JdbcTemplate jdbcTemplate;

    public JDBCTraitDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Trait> listAllTraits() throws DaoException {
        List<Trait> traitList = new ArrayList<>();
        String sql = "select * from trait order by trait_name ASC"; // TODO edit later
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
        String sql = "select * from Trait where trait_id = ?"; // edit
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
    public List<Trait> getTraitsByBreed(int breedId) throws DaoException {
        List<Trait> traitList = new ArrayList<>();
        String sql = "select trait.trait_id,trait.trait_name " +
                "from trait " +
                "inner join breed_trait ON breed_trait.trait_id = trait.trait_id " +
                "where breed_trait.breed_id=?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, breedId);
        while (rs.next()) {
            Trait tempTrait = new Trait();
            tempTrait.setTraitId(rs.getInt("trait_id"));
            tempTrait.setTraitName(rs.getString("trait_name"));
            traitList.add(tempTrait);
        }
        return traitList;
    }


    // MApRow
    public Trait mapRowToTrait(SqlRowSet rs) {
        Trait trait = new Trait();
       trait.setTraitId(rs.getInt("trait_id"));
       trait.setTraitName(rs.getString("trait_name"));
       return trait;
    }
}
