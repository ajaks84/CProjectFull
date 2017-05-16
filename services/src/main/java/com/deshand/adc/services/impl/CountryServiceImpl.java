package com.deshand.adc.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.ICountryDao;
import com.deshand.adc.datamodel.Country;
import com.deshand.adc.services.services.ICountryService;


@Service
public class CountryServiceImpl implements ICountryService{
	
	@Inject	
    private ICountryDao countryDao;

    public List<Country> getAll() {
        return countryDao.getAll();
    }

    public Country save(Country country) {
        if (country.getId() == null) {
            countryDao.insert(country);
        } else {
        	 countryDao.update(country);
        }
		return country;
    }

    public void delete(Integer id) {
    	countryDao.delete(id);
    }


	public Country get(Integer id) {
		return countryDao.get(id);
	}

}
