package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.model.Dog;

import java.security.Principal;
import java.util.List;

import com.techelevator.dao.DogDao;
import com.techelevator.dao.UserDao;

@RestController
@CrossOrigin
//@@PreAuthorize("isAuthenticated()")
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/{dogId}")
    public Dog getDogById(@PathVariable int dogId) throws DaoException {
        return dogDao.getDogById(dogId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/dogs", method = RequestMethod.POST)
    public Dog createDog(Dog dog) throws DaoException {
        return dogDao.createDog(dog);
    }

    /**
     * Updates a dog.
     *
     * @param dog the dog to update
     * @return the updated dog
     * @throws DaoException if there is an error accessing the data
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/dogs")
    public Dog updateDog(@RequestBody Dog dog) throws DaoException {
        return dogDao.updateDog(dog);
    }

    /**
     * Retrieves a random dog.
     *
     * @return a random dog
     * @throws DaoException if there is an error accessing the data
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/random")
    public Dog getRandomDog() throws DaoException {
        return dogDao.getRandomDog();
    }

    /**
     * Retrieves preview dogs list.
     *
     * @return a preview dog list
     * @throws DaoException if there is an error accessing the data
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/preview")
    public List<Dog> getPreviewDog() throws DaoException {
        return dogDao.getPreviewDog();
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(path = "/userdogsyes")
//    public List<Dog> getDogsYes(Principal principal) throws DaoException {
//        return userDao.getSwipedYesDogs(userDao.getUserByUsername(principal.getName()).getId());
//    }
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(path = "/userdogsno")
//    public List<Dog> getDogsNo(Principal principal) throws DaoException {
//        return userDao.getSwipedNoDogs(userDao.getUserByUsername(principal.getName()).getId());
//    }
//


}
