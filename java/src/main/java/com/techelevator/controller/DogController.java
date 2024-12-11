package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.model.Dog;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.DogDao;
import com.techelevator.dao.UserDao;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DogController {
    @Autowired
    private DogDao dogDao;
    @Autowired
    private UserDao userDao;

    /**
     * Retrieves all dogs.
     *
     * @return a list of all dogs
     * @throws DaoException if there is an error accessing the data
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs")
    public List<Dog> getAllDogs() throws DaoException {
        return dogDao.getAllDogs();
    }

    /**
     * Retrieves a dog by its ID.
     *
     * @param dogId the ID of the dog
     * @return the retrieved dog
     * @throws DaoException if there is an error accessing the data
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/{dogId}")
    public Dog getDogById(@PathVariable int dogId) throws DaoException {
        return dogDao.getDogById(dogId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/dogs", method = RequestMethod.POST)
    public Dog createDog(@RequestBody Dog dog) throws DaoException {
        return dogDao.createDog(dog);
    }

    /**
     * Updates a dog.
     *
     * @param dog the dog to update
     * @return the updated dog
     * @throws DaoException if there is an error accessing the data
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/dogs")
    public Dog updateDog(@RequestBody Dog dog) throws DaoException {
        return dogDao.updateDog(dog);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/dogs/{dogId}", method = RequestMethod.DELETE)
    public void deleteDog(@PathVariable int dogId) throws DaoException {
        dogDao.deleteDog(dogId);
    }

    /**
     * Retrieves a random dog.
     *
     * @return a random dog
     * @throws DaoException if there is an error accessing the data
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/random")
    public Dog getRandomDog() throws DaoException {
        return dogDao.getRandomDog();
    }


}
