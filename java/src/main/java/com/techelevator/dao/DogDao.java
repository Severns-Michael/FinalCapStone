package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Dog;

import java.util.List;

public interface DogDao {
    List<Dog> getAllDogs() throws DaoException;

    Dog getDogById(int id) throws DaoException;

    Dog createDog(Dog dog) throws DaoException;

    Dog updateDog(Dog dog) throws DaoException;
    void deleteDog(int dogId) throws DaoException;

    Dog getRandomDog() throws DaoException;

}
