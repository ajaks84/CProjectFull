package com.deshand.adc.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.ILineDao;
import com.deshand.adc.datamodel.Line;
import com.deshand.adc.services.services.ILineService;

@Service
public class LineServiceImpl implements ILineService{
	
	@Inject
    private ILineDao lineDao;

	public Line get(Integer id) {
		return lineDao.get(id);
	}

	public Line save(Line line) {
		 if (line.getId() == null) {
	            lineDao.insert(line);
	        } else {
	        	lineDao.update(line);
	        }
		return line;
	    }
	

	public void saveMultiple(Line... lineArray) {
		for (Line line : lineArray) {
            save(line);
        }
		
	}

	public List<Line> getAll() {
		
		return lineDao.getAll();
	}

	public void delete(Integer id) {
		lineDao.delete(id);
		
	}

	@Override
	public List<Line> getLineByFactory(Integer factoryId) {
		
		return lineDao.getLineByFactory(factoryId);
	}

}
