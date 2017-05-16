package com.deshand.adc.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.IFactoryDao;
import com.deshand.adc.datamodel.Factory;
import com.deshand.adc.services.services.IFactoryService;


@Service
public class FactoryServiceImpl implements IFactoryService{
	
	@Inject	
    private IFactoryDao factoryDao;

	@Override
	public Factory get(Integer id) {
		return factoryDao.get(id);
	}

	@Override
	public Factory save(Factory factory) {
		if (factory.getId() == null) {
            factoryDao.insert(factory);
        } else {
        	factoryDao.update(factory);
        }
	return factory;
	}

	@Override
	public List<Factory> getAll() {
		return factoryDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		factoryDao.delete(id);
		
	}
	
	@Override
	public List<Factory> getByCountry(Integer id) {
		return factoryDao.getByCountry(id);
	}
	
	

}
