package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.Trait;
import com.techelevator.model.User;

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
}
