package com.techelevator.controller;

import com.techelevator.dao.BreedDao;
import com.techelevator.dao.TraitDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.model.Swiped;
import com.techelevator.dao.UserDao;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
//@@PreAuthorize("isAuthenticated()")
public class BreedController {
    @Autowired
    private BreedDao breedDao;
    private String BASE_URL = "/breed";
    @Autowired
    private TraitDao traitDao;
    @Autowired
    private UserDao userDao;


    /**
     * Retrieves all available breeds from the database.
     *
     * @return a list of all breeds
     * @throws DaoException if there is an error accessing the data
     */
    @RequestMapping(path = "/breeds", method = RequestMethod.GET)
    public List<Breed> getALLBreeds() throws DaoException {
        return breedDao.listAllBreeds();
    }

    /**
     * Retrieves the breed with the specified id, including its traits.
     *
     * @param breedId the id of the breed to return
     * @return the breed with the specified id
     * @throws ResponseStatusException if the breed is not found
     */
    @RequestMapping(path = "/breeds/{breedId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Breed getBreedById(@PathVariable int breedId) {
        Breed returnedBreed = new Breed();
        try {
            returnedBreed = breedDao.getBreedById(breedId);
            returnedBreed.setTraits(traitDao.getTraitsByBreed(breedId));

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Breed not found");
        }
        return returnedBreed;

    }


    /**
     * Adds a new breed to the database.
     *
     * @param breed the breed you would like to create
     * @return the created breed
     * @throws DaoException if there is an error during the creation process
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/breeds", method = RequestMethod.POST)
    public Breed addBreed(@RequestBody Breed breed) throws DaoException {
        return breedDao.createBreed(breed);
    }

    /**
     * Updates the traits of a specified breed.
     *
     * @param breed the breed with updated traits
     * @return the updated breed
     * @throws DaoException if there is an error during the update process
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/breeds", method = RequestMethod.PUT)
    public Breed updateBreedTraits(@RequestBody Breed breed) throws DaoException {
        return breedDao.updateTraitForBreed(breed);
    }


    /**
     * Deletes the breed with the specified id from the database.
     *
     * @param breedId the id of the breed to delete
     * @throws DaoException if there is an error during the deletion process
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/breeds/{breedId}", method = RequestMethod.DELETE)
    public void deleteBreed(@PathVariable int breedId) throws DaoException {
        breedDao.deleteBreed(breedId);
    }

    /**
     * Retrieves the list of breeds swiped by the current user.
     *
     * @param principal the security principal of the user
     * @return a list of swiped breeds
     * @throws DaoException if there is an error accessing the data
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/swiped")
    public List<Swiped> getSwipedBreeds(Principal principal) throws DaoException {
        return userDao.getAllSwiped(userDao.getUserByUsername(principal.getName()).getId());
    }

    /**
     * Adds a breed to the user's swiped list.
     *
     * @param swiped the swiped breed information
     * @return the added swiped breed
     * @throws DaoException if there is an error during the creation process
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/swiped")
    public Swiped addSwipedBreed(@RequestBody Swiped swiped) throws DaoException {
        return userDao.addSwiped(swiped);
    }

    /**
     * Retrieves a list of random breeds that the user has not swiped on.
     *
     * @param principal the security principal of the user
     * @return a list of random breeds
     * @throws DaoException if there is an error accessing the data
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/breeds/random")
    public List<Breed> getRandomBreeds(Principal principal) throws DaoException {
        return userDao.getBreedUserHasntSwiped(userDao.getUserByUsername(principal.getName()).getId());
    }
}

