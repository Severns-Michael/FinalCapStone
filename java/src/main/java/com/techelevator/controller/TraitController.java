package com.techelevator.controller;
import com.techelevator.dao.TraitDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class TraitController {
    @Autowired
    private TraitDao traitDao;
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

    /**
     * path = /traits/traitId
     * @param traitId the id of the trait to return
     * @return returns the trait with the specified id
     */
    @RequestMapping(path = "/traits/{traitId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Trait getTraitById(@PathVariable int traitId) {
        if (traitDao.getTraitById(traitId).getTraitId()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trait not found");
        } else {
            return traitDao.getTraitById(traitId);
        }
    }

}
