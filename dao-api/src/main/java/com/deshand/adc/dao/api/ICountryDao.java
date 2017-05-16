package com.deshand.adc.dao.api;

import java.util.List;

import com.deshand.adc.datamodel.Country;

public interface ICountryDao {

	Country get(Integer id);

	Country insert(Country country);

	Country update(Country country);

	List<Country> getAll();

	void delete(Integer id);
}
