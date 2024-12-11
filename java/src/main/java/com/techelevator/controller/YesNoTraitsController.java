package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.techelevator.dao.UserDao;


import java.security.Principal;
import java.util.List;

public class YesNoTraitsController {
    @Autowired
    private UserDao userDao;

    /**
     * Gets traits that are included for the current user.
     *
     * @param principal the security principal of the user
     * @return a list of included traits
     * @throws DaoException if there is an error accessing data
     */
    @GetMapping(path = "/traits/include")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getYesTraitsByUser(Principal principal) throws DaoException {
        return userDao.getYesTraits(userDao.getUserByUsername(principal.getName()).getId());
    }

    /**
     * Gets traits that are excluded for the current user.
     *
     * @param principal the security principal of the user
     * @return a list of excluded traits
     * @throws DaoException if there is an error accessing data
     */
    @GetMapping(path = "/traits/exclude")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getNoTraitsByUser(Principal principal) throws DaoException {
        return userDao.getNoTraits(userDao.getUserByUsername(principal.getName()).getId());
    }

    /**
     * Updates the traits that are marked as "Yes" for the current user.
     *
     * @param yesTraits list of traits to set as "Yes"
     * @param principal the security principal of the user
     * @return the updated list of "Yes" traits
     */
    @PutMapping(path = "/traits/include")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> updateYesTraitsForUser(@RequestBody List<Trait> yesTraits, Principal principal) {
//        System.out.println(YesTraits());
        return userDao.setUserYesTraits(yesTraits, principal);
    }

    /**
     * Updates the traits that are marked as "No" for the current user.
     *
     * @param noTraits list of traits to set as "No"
     * @param principal the security principal of the user
     * @return the updated list of "No" traits
     */
    @PutMapping(path = "/traits/exclude")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> updateNoTraitsForUser(@RequestBody List<Trait> noTraits, Principal principal) {
        return userDao.setUserNoTraits(noTraits, principal);
    }
}

