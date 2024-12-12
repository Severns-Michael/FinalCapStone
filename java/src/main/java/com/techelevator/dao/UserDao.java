package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
    User createAdmin(RegisterUserDto user);
    void promoteUser(User user);
    void demoteUser(User user);
    void deleteUser(User user);

    List<Trait> getYesTraits(int userId);

    List<Trait> getNoTraits(int userId);

    List<Trait> setUserYesTraits(List<Trait> yesTraits, Principal principal) throws DaoException;

    List<Trait> setUserNoTraits(List<Trait> noTraits, Principal principal) throws DaoException;

    List<Swiped> getAllSwiped(int userId) throws DaoException;

    Swiped addSwiped(Swiped swiped) throws DaoException;

    List<Breed> getBreedUserHasntSwiped(int userId) throws DaoException;

    List<Dog> getPotentialDogs(int userId) throws DaoException;


}
