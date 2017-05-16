package com.deshand.adc.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IFactoryDao;
import com.deshand.adc.datamodel.Factory;

@Repository
public class FactoryDaoXmlImpl implements IFactoryDao{

	@Override
	public Factory get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factory insert(Factory factory) {
		// TODO Auto-generated method stub
		return factory;
	}

	@Override
	public Factory update(Factory factory) {
		return factory;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Factory> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Factory> getByCountry(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
