package com.techelevator.controller;
import com.techelevator.dao.BreedDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
//@@PreAuthorize("isAuthenticated()")
public class BreedController {
    @Autowired
    private BreedDao breedDao;
    private String BASE_URL = "/breed";



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
        if (breedDao.getBreedById(breedId).getBreedId()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Breed not found");
        } else {
            return breedDao.getBreedById(breedId);
        }

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

//    /**
//     * /breeds
//     * @param breed breed you would like to update.
//     * @return the updated breed.
//     * @throws DaoException
//     */
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path = "/breeds/{breedId}", method = RequestMethod.PUT)
//    public Breed updateBreed(@PathVariable Breed breed) throws DaoException {
//        return breedDao.updateBreed(breed);
//    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/breeds", method = RequestMethod.PUT)
    public Breed updateTraitForBreed(@RequestBody Breed breed) throws DaoException {
        return breedDao.updateTraitForBreed(breed , breed.getTraits());
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

}

