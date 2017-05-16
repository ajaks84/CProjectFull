package com.deshand.adc.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IConsumptionDao;
import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;

@Repository
public class ConsumptionDaoXxlImpl implements IConsumptionDao {

	@Override
	public Consumption get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consumption insert(Consumption consumption) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consumption update(Consumption consumption) {
		return consumption;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ConsumptionWithMaterial> getByReportId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Consumption> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
