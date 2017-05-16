package com.deshand.adc.services.services;

import java.util.List;

import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;

public interface IConsumptionService {
	
	Consumption get(Integer id);

	Consumption save(Consumption consumption);

	void delete(Integer id);
	
	List<ConsumptionWithMaterial> getByReportId(Integer id);

	List<Consumption> getAll();

}
