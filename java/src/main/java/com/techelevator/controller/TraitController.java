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

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TraitController {
    @Autowired
    private TraitDao traitDao;
    @Autowired
    private UserDao userDao;
    private String BASE_URL = "/trait";



    /**
     * /breeds
     * @return a list of traits
     * @throws DaoException
     */
    @RequestMapping(path = "/traits", method = RequestMethod.GET)
    public List<Trait> getAllTraits() throws DaoException {
        return traitDao.listAllTraits();
    }

    @GetMapping(path="/users")
    public List<User> getAllUsers(){
        return userDao.getUsers();
    }

    /**
     * path = /traits/traitId
     * @param traitId the id of the trait to return
     * @return returns the trait with the specified id
     */
    @RequestMapping(path = "/traits/{traitId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Trait getTraitById(@PathVariable int traitId) throws DaoException{
        return traitDao.getTraitById(traitId);
    }

    @GetMapping(path="/traits/{userId}/include")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getYesTraitsByUser(@PathVariable int userId) throws DaoException{
        return userDao.getYesTraits(userId);
    }

    @GetMapping(path="/traits/{userId}/exclude")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getNoTraitsByUser(@PathVariable int userId) throws DaoException{
        return userDao.getNoTraits(userId);
    }

    @PutMapping(path="/traits/include")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> updateYesTraitsForUser(@RequestBody User user){
        return userDao.setUserYesTraits(user);
    }
    @PutMapping(path="/traits/exclude")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> updateNoTraitsForUser(@RequestBody User user){
        return userDao.setUserNoTraits(user);
    }
}
