package com.deshand.adc.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.ILineDao;
import com.deshand.adc.datamodel.Line;

@Repository
public class LineDaoXmlImpl implements ILineDao{

	@Override
	public Line get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Line insert(Line line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Line update(Line line) {
		return line;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Line> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Line> getLineByFactory(Integer factoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
