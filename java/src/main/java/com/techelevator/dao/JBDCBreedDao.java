package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Breed;
import com.techelevator.model.Dog;
import com.techelevator.model.PreviewBreed;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.model.Trait;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JBDCBreedDao implements BreedDao {
    private final JdbcTemplate jdbcTemplate;

    public JBDCBreedDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Breed> listAllBreeds() throws DaoException {
        List<Breed> breedList = new ArrayList<>();
        String sql = "SELECT b.breed_id, b.breed_name, b.sub_breed, b.official_name " +
                "from breed b " +
                "order by official_name ASC"; //need to edit
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                breedList.add(mapRowToBreed(rs));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breedList;
    }

    @Override
    public Breed getBreedById(int id) throws DaoException {
        Breed breed = new Breed();
        String sql = "SELECT b.breed_id, b.breed_name, b.sub_breed, b.official_name, bt.trait_id, t.trait_name " +
                "from breed b " +
                "full join breed_trait bt ON bt.breed_id = b.breed_id " +
                "full join trait t ON t.trait_id = bt.trait_id " +
                "WHERE b.breed_id=?"; // need to edit
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                breed = mapRowToSingleBreed(rs);
//                breed = mapRowToBreed(rs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breed;
    }

    @Override
    public Breed createBreed(Breed breed) throws DaoException {
        int breedId;
        String sql = "INSERT INTO breed (breed_name,sub_breed,official_name) VALUES (?,?,?) RETURNING breed_id"; // check
        try {
            breedId = jdbcTemplate.queryForObject(sql, int.class, breed.getBreedName(), breed.getSubBreed(), breed.getOfficialName());
            breed = getBreedById(breedId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return breed;
    }

    @Override
    public void deleteBreed(int breedId) throws DaoException {
        String sql = "delete from breed_trait where breed_id = ?";
        String sql2 = "delete from breed where breed_id = ?";
        try {
            jdbcTemplate.update(sql, breedId);
            jdbcTemplate.update(sql2, breedId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


    }

    @Override
    public Breed updateTraitForBreed(Breed breed) throws DaoException {
        String sqlDelete = "delete from breed_trait where breed_id=?;";
        String sqlInsert = "insert into breed_trait (breed_id,trait_id) values(?,?);";
        try {
            jdbcTemplate.update(sqlDelete, breed.getBreedId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        for (Trait trait : breed.getTraits()) {
            try {
                jdbcTemplate.update(sqlInsert, breed.getBreedId(), trait.getTraitId());

            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            }
        }
        return getBreedById(breed.getBreedId());
//        return breed;
    }

    @Override
    public List<PreviewBreed> getPreviewBreeds() throws DaoException {
        List<PreviewBreed> previewBreedList = new ArrayList<>();
        String sql = "select * from preview_breeds";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                PreviewBreed pb = new PreviewBreed();
                pb.setPreviewBreedId(rs.getInt("preview_breed_id"));
                pb.setBreedId(rs.getInt("breed_id"));
                previewBreedList.add(pb);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return previewBreedList;
    }


    // MApRow
    public Breed mapRowToSingleBreed(SqlRowSet rs) {
        Breed breed = new Breed();
        List<Trait> traitList = new ArrayList<>();
        breed.setBreedId(rs.getInt("breed_id"));
        breed.setBreedName(rs.getString("breed_name"));
        breed.setSubBreed(rs.getString("sub_breed"));
        breed.setOfficialName(rs.getString("official_name"));
        traitList.add(new Trait(rs.getInt("trait_id"),
                rs.getString("trait_name")));
        while (rs.next()) {
            Trait trait = new Trait(rs.getInt("trait_id"),
                    rs.getString("trait_name"));
            traitList.add(trait);
        }
        breed.setTraits(traitList);
        return breed;
    }

    public Breed mapRowToBreed(SqlRowSet rs) {
        Breed breed = new Breed();
        breed.setBreedId(rs.getInt("breed_id"));
        breed.setBreedName(rs.getString("breed_name"));
        breed.setSubBreed(rs.getString("sub_breed"));
        breed.setOfficialName(rs.getString("official_name"));
        return breed;
    }
//    public Breed mapRowToBreedWithTraitList(SqlRowSet ra) throws SQLException {
//        Breed breed = new Breed();
//        List<Trait> traitList = new ArrayList<>();
//        breed.setBreedId(ra.getInt("breed_id"));
//        breed.setBreedName(ra.getString("breed_name"));
//        breed.setSubBreed(ra.getString("sub_breed"));
//        breed.setOfficialName(ra.getString("official_name"));
//        while (ra.next()) {
//            traitList.add(JDBCTraitDao.getTraitById(ra.getInt("trait_id")));
//        }
//
//    }
}
