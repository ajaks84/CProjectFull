package com.deshand.adc.dao.api;

import java.util.List;

import com.deshand.adc.datamodel.Factory;

public interface IFactoryDao {
	
	Factory get(Integer id);

	Factory insert(Factory factory);

	Factory update(Factory factory);

	List<Factory> getAll();

	void delete(Integer id);
	
	List<Factory> getByCountry(Integer id);

}
