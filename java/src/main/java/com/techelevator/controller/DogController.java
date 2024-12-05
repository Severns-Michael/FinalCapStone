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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs")
    public List<Dog> getAllDogs() throws DaoException {
        return dogDao.getAllDogs();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/{dogId}")
    public Dog getDogById(@PathVariable int dogId) throws DaoException {
        return dogDao.getDogById(dogId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/dogs")
    public Dog updateDog(@RequestBody Dog dog) throws DaoException {
        return dogDao.updateDog(dog);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dogs/random")
    public Dog getRandomDog() throws DaoException {
        return dogDao.getRandomDog();
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
