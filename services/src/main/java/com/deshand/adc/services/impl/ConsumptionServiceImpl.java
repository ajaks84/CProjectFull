package com.deshand.adc.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.IConsumptionDao;
import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;
import com.deshand.adc.services.services.IConsumptionService;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {

	@Inject
	private IConsumptionDao consumptionDao;

	@Override
	public Consumption get(Integer id) {
		return consumptionDao.get(id);
	}

	@Override
	public Consumption save(Consumption consumption) {
		if (consumption.getId() == null) {
			consumptionDao.insert(consumption);
		} else {
			consumptionDao.update(consumption);
		}
		return consumption;
	}

	@Override
	public void delete(Integer id) {
		consumptionDao.delete(id);
	}

	@Override
	public List<ConsumptionWithMaterial> getByReportId(Integer reportId) {
		return consumptionDao.getByReportId(reportId);
	}

	@Override
	public List<Consumption> getAll() {
		return consumptionDao.getAll();
	}

}
