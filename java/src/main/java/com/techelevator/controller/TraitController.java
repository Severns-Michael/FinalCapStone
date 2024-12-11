package com.techelevator.controller;

import com.techelevator.dao.TraitDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TraitController {
    @Autowired
    private TraitDao traitDao;
    @Autowired
    private UserDao userDao;


    /**
     * /breeds
     *
     * @return a list of traits
     * @throws DaoException
     */
    @RequestMapping(path = "/traits", method = RequestMethod.GET)
    public List<Trait> getAllTraits() throws DaoException {
        return traitDao.listAllTraits();
    }

    /**
     * Fetches all users.
     *
     * @return a list of users
     */
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the id of the user to be retrieved
     * @return the user with the specified ID
     */
    @GetMapping(path = "/users/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userDao.getUserById(userId);
    }

    /**
     * Retrieves a trait by its ID.
     *
     * @param traitId the id of the trait to return
     * @return returns the trait with the specified id
     * @throws DaoException if there is an error accessing data
     */
    @RequestMapping(path = "/traits/{traitId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Trait getTraitById(@PathVariable int traitId) throws DaoException {
        return traitDao.getTraitById(traitId);
    }
}
