package com.deshand.adc.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.ICountryDao;
import com.deshand.adc.datamodel.Country;


@Repository
public class CountryDaoXmlImpl implements ICountryDao{

	@Override
	public Country get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country insert(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country update(Country country) {
		return country;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Country> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
