package com.deshand.adc.services.services;

import java.util.List;

import com.deshand.adc.datamodel.Factory;

public interface IFactoryService {
	
	Factory get(Integer id);

	Factory save(Factory factory);

    List<Factory> getAll();

    void delete(Integer id);

	List<Factory> getByCountry(Integer id);

}
