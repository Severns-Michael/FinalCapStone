package com.techelevator.dao;
import com.techelevator.model.Trait;
import com.techelevator.exception.DaoException;
import java.util.List;

public interface TraitDao {
    List<Trait> listAllTraits() throws DaoException;
    Trait getTraitById(int id) throws DaoException;
    Trait createTrait(Trait trait) throws DaoException;
    Trait updateTrait(Trait trait) throws DaoException;
    void deleteTrait(int traitsId) throws DaoException;
}
