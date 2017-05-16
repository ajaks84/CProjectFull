package com.deshand.adc.dao.api;

import java.util.List;

import com.deshand.adc.datamodel.Line;

public interface ILineDao {

	Line get(Integer id);

	Line insert(Line line);

	Line update(Line line);

	List<Line> getAll();

	void delete(Integer id);

	List<Line> getLineByFactory(Integer factoryId);

}
