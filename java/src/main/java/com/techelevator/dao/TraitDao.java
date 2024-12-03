package com.techelevator.dao;
import com.techelevator.model.Breed;
import com.techelevator.model.Trait;
import com.techelevator.exception.DaoException;
import java.util.List;

public interface TraitDao {
    List<Trait> listAllTraits() throws DaoException;
    Trait getTraitById(int id) throws DaoException;
 List<Trait> getTraitsByBreed(int breedId) throws DaoException;
}
