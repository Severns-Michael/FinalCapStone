package com.techelevator.controller;
import com.techelevator.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.model.Dog;
import java.util.List;
import com.techelevator.dao.DogDao;

@RestController
@CrossOrigin
//@@PreAuthorize("isAuthenticated()")
public class DogController {
    @Autowired
    private DogDao dogDao;

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



}
