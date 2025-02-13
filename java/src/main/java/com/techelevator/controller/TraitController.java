package com.techelevator.controller;

import com.techelevator.dao.TraitDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class TraitController {
    @Autowired
    private TraitDao traitDao;


    /**
     * /breeds
     *
     * @return a list of traits
     * @throws DaoException
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/traits", method = RequestMethod.GET)
    public List<Trait> getAllTraits() throws DaoException {
        return traitDao.listAllTraits();
    }


    /**
     * Retrieves a trait by its ID.
     *
     * @param traitId the id of the trait to return
     * @return returns the trait with the specified id
     * @throws DaoException if there is an error accessing data
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/traits/{traitId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Trait getTraitById(@PathVariable int traitId) throws DaoException {
        return traitDao.getTraitById(traitId);
    }
}
