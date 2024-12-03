package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Breed;
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
public class JBDCBreedDao implements BreedDao{
    private final JdbcTemplate jdbcTemplate;
    public JBDCBreedDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Breed> listAllBreeds() throws DaoException {
        List<Breed> breedList = new ArrayList<>();
        String sql = "SELECT * FROM breed"; //need to edit
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                breedList.add(mapRowToBreed(rs));
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breedList;
    }

    @Override
    public Breed getBreedById(int id) throws DaoException {
        Breed breed = new Breed();
        String sql = "SELECT * FROM breed WHERE breed_id = ?"; // need to edit
        try{
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                breed = mapRowToBreed(rs);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breed;
    }

    @Override
    public Breed createBreed(Breed breed) throws DaoException {
        int breedId;
        String sql = "INSERT INTO breed (breed_name,sub_breed,official_name) VALUES (?,?,?) RETURNING breed_id"; // check
        try{
            breedId = jdbcTemplate.queryForObject(sql, int.class, breed.getBreedName(), breed.getSubBreed(), breed.getOfficialName());
            breed = getBreedById(breedId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return breed;
    }

//    @Override
//    public Breed updateBreed(Breed breed) throws DaoException {
//        String sql = "update breed set breed_name = ?, sub_breed = ? where breed_id = ?";// edit
//        try{
//           int rows= jdbcTemplate.update(sql, breed.getBreedName(), breed.getSubBreed());
//            if(rows!=1) {
//                throw new DaoException("Unable to update breed");
//            }
//            breed= getBreedById(breed.getBreedId());
//
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//
//        }  catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//        return breed;
//    }

    @Override
    public void deleteBreed(int breedId) throws DaoException {
        String sql = "delete from Breed where breed_id = ?";
        String sql2 = "delete from Breed_trait where breed_id = ?";
        try{
            jdbcTemplate.update(sql, breedId);
            jdbcTemplate.update(sql2, breedId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


    }

    @Override
    public Breed updateTraitForBreed(int breedId, int traitId) throws DaoException {
        String sql = "update breed_trait set trait_id = ? where breed_id = ?";
        try{
            jdbcTemplate.update(sql, traitId, breedId);
            return getBreedById(breedId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    // MApRow
    public Breed mapRowToBreed(SqlRowSet rs) {
        Breed breed = new Breed();
        breed.setBreedId(rs.getInt("breed_id"));
        breed.setBreedName(rs.getString("breed_name"));
        breed.setSubBreed(rs.getString("sub_breed"));
        breed.setOfficialName(rs.getString("official_name"));
        return breed;
    }
}
