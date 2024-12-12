package com.techelevator.controller;

import com.techelevator.dao.AdoptionAgencyDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.AdoptionAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AdoptionAgencyController {

    @Autowired
    private AdoptionAgencyDao aaDao;

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/agencies", method = RequestMethod.GET)
    public List<AdoptionAgency> getAdoptionAgencies() throws DaoException {
        return aaDao.getAgencies();
    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/agencies/{agencyId}", method = RequestMethod.GET)
    public AdoptionAgency getAgencyById(@PathVariable int agencyId) throws DaoException {
        return aaDao.getAgencyById(agencyId);
    }

}
