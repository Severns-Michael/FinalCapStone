package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.Trait;
import com.techelevator.model.User;
import com.techelevator.model.Dog;
import com.techelevator.model.Breed;
import com.techelevator.model.Swiped;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    List<Trait> getYesTraits(int userId);
    List<Trait> getNoTraits(int userId);

    List<Trait> setUserYesTraits(List<Trait> yesTraits, Principal principal) throws DaoException;
    List<Trait> setUserNoTraits(List<Trait> noTraits, Principal principal) throws DaoException;

    List<Swiped> getAllSwiped(int userId) throws DaoException;

    Swiped addSwiped(Swiped swiped) throws DaoException;






//    List<Dog> getSwipedYesDogs(int userId);
//    List<Dog> setSwipedYesDogs(List<Dog> favoriteDogs, Principal principal) throws DaoException;
//
//    List<Dog> getSwipedNoDogs(int userId);
//    List<Dog> setSwipedNoDogs(List<Dog> favoriteDogs, Principal principal) throws DaoException;
}
