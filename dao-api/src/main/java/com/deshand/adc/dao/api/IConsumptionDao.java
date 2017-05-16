package com.deshand.adc.dao.api;

import java.util.List;

import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;

public interface IConsumptionDao {
	
	Consumption get(Integer id);

	Consumption insert(Consumption consumption);

	Consumption update(Consumption consumption);

	void delete(Integer id);
	
	List<ConsumptionWithMaterial> getByReportId(Integer id);

	List<Consumption> getAll();

}
