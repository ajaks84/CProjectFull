package com.deshand.adc.services.services;

import java.util.List;

import com.deshand.adc.datamodel.Country;

public interface ICountryService {
	
	Country get(Integer id);

    Country save(Country country);

    List<Country> getAll();

    void delete(Integer id);

}
