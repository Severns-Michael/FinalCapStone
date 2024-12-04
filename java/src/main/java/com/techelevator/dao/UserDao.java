package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.Trait;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    List<Trait> getYesTraits(int userId);
    List<Trait> getNoTraits(int userId);

    List<Trait> UserYesTraits(User user) throws DaoException;
    List<Trait> UserNoTraits(User user) throws DaoException;
}
