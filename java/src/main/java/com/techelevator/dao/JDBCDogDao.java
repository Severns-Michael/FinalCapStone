package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Dog;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.exception.DaoException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCDogDao implements DogDao {
    private final JdbcTemplate jdbcTemplate;

    public JDBCDogDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Dog> getAllDogs() throws DaoException {
        List<Dog> dogList = new ArrayList<>();
        String sql = "select * from dog";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                dogList.add(mapRowToDog(rs));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return dogList;
    }

    @Override
    public Dog getDogById(int id) throws DaoException {
        Dog dog = new Dog();
        String sql = "select * from dog where dog_id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                dog = mapRowToDog(rs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return dog;
    }

    @Override
    public Dog updateDog(Dog dog) throws DaoException {
        String sql = "update dog set dog_name = ?, breed_id = ?, img = ?, age = ?, size = ?, agency_id = ?, gender = ?";
        try {
            jdbcTemplate.update(sql, dog.getDogName(), dog.getBreedId(), dog.getImg(), dog.getAge(), dog.getSize(), dog.getAgencyId(), dog.getGender());
            dog = getDogById(dog.getDogId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return dog;
    }

    @Override
    public Dog getRandomDog() throws DaoException { //TODO wrap into user dog yes and no
        return null;
    }

    @Override
    public List<Dog> getPreviewDog() throws DaoException {
        List<Dog> previewDogList = new ArrayList<>();
        String sql = "select * from preview_dog";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                previewDogList.add(mapRowToDog(rs));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return previewDogList;
    }

    public Dog mapRowToDog(SqlRowSet rs) {
        Dog dog = new Dog();
        dog.setDogId(rs.getInt("dog_id"));
        dog.setDogName(rs.getString("dog_name"));
        dog.setBreedId(rs.getInt("breed_id"));
        dog.setImg(rs.getString("img"));
        dog.setAge(rs.getInt("age"));
        dog.setSize(rs.getInt("size"));
        dog.setAgencyId(rs.getInt("agency_id"));
        dog.setGender(rs.getInt("gender"));
        return dog;
    }
}
