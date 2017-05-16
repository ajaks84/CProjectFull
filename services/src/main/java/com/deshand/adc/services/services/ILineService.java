package com.deshand.adc.services.services;

import java.util.List;

import com.deshand.adc.datamodel.Line;

public interface ILineService {
	
    Line get(Integer id);

	Line save(Line line);

	List<Line> getAll();
	
	List<Line> getLineByFactory(Integer factoryId);

	void delete(Integer id);
	
	
}