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
     * /breeds
     * @return a list of breeds
     * @throws DaoException
     */

    @RequestMapping(path="/breeds", method = RequestMethod.GET)
    public List<Breed> getALLBreeds() throws DaoException {
        return breedDao.listAllBreeds();
    }
    /**
     * path = /breeds/breedId
     * @param breedId the id of the breed to return
     * @return returns the breed with the specified id
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
     * /breeds
     * @param breed you would like to create
     * @return created breed
     */

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path= "/breeds", method = RequestMethod.POST)
    public Breed addBreed(@RequestBody Breed breed) throws DaoException {
        return breedDao.createBreed(breed);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/breeds", method = RequestMethod.PUT)
    public Breed updateBreedTraits(@RequestBody Breed breed) throws DaoException {
        return breedDao.updateTraitForBreed(breed);
    }


    /**
     * path = /breeds/breedId
     * @param breedId the id of the breed to return
     * @return returns the breed with the specified id
     */

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/breeds/{breedId}", method = RequestMethod.DELETE)
    public void deleteBreed(@PathVariable int breedId) throws DaoException {
        breedDao.deleteBreed(breedId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path= "/swiped")
    public List<Swiped> getSwipedBreeds(Principal principal) throws DaoException{
        return userDao.getAllSwiped(userDao.getUserByUsername(principal.getName()).getId());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/swiped")
    public Swiped addSwipedBreed(@RequestBody Swiped swiped) throws DaoException{
        return userDao.addSwiped(swiped);
    }
}

