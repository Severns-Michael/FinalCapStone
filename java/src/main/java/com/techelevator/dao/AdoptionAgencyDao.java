package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.AdoptionAgency;

import java.util.List;

public interface AdoptionAgencyDao {

    List<AdoptionAgency> getAgencies() throws DaoException;
    AdoptionAgency getAgencyById(int agencyId) throws DaoException;

}
