package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Breed;
import com.techelevator.model.Trait;
import java.util.List;


public interface BreedDao {
    List<Breed> listAllBreeds() throws DaoException;
    Breed getBreedById(int id) throws DaoException;
    Breed createBreed(Breed breed) throws DaoException;
    void deleteBreed(int breedId) throws DaoException;
    Breed updateTraitForBreed(Breed breed) throws DaoException;


}
