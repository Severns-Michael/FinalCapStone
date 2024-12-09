package com.techelevator.controller;

import com.techelevator.dao.AdoptionAgencyDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.AdoptionAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
//@@PreAuthorize("isAuthenticated()")
public class AdoptionAgencyController {

    @Autowired
    private AdoptionAgencyDao aaDao;

    @RequestMapping(path = "/agencies", method = RequestMethod.GET)
    public List<AdoptionAgency> getAdoptionAgencies() throws DaoException {
        return aaDao.getAgencies();
    }

    @RequestMapping(path = "/agencies/{agencyId}", method = RequestMethod.GET)
    public AdoptionAgency getAgencyById(int agencyId) throws DaoException {
        return aaDao.getAgencyById(agencyId);
    }

}
