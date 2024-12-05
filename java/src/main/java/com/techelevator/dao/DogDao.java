package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Dog;

import java.util.List;

public interface DogDao {
    List<Dog> getAllDogs() throws DaoException;
    Dog getDogById(int id) throws DaoException;

}
