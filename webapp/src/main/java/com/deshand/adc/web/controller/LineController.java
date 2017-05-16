package com.deshand.adc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deshand.adc.datamodel.Line;
import com.deshand.adc.services.services.ILineService;
import com.deshand.adc.web.model.LineModel;

@RestController
@RequestMapping("/lines")
public class LineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LineController.class);

	private String ctrlName = this.getClass().getSimpleName();

	@Inject
	private ILineService service;

	@RequestMapping(value = "/by{factoryId}", method = RequestMethod.GET)
	public ResponseEntity<List<LineModel>> getByCountry(@PathVariable("factoryId") Integer factoryId) {
		List<Line> lines = service.getLineByFactory(factoryId);
		LOGGER.error(ctrlName + lines);
		List<LineModel> converted = new ArrayList<>();
		for (Line line : lines) {
			converted.add(entity2model(line));
		}
		return new ResponseEntity<List<LineModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LineModel>> getAll() {
		List<Line> all = service.getAll();
		LOGGER.error(ctrlName + all);
		List<LineModel> converted = new ArrayList<>();
		for (Line line : all) {
			converted.add(entity2model(line));
		}
		return new ResponseEntity<List<LineModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LineModel> getById(@PathVariable Integer id) {
		Line line = service.get(id);
		return new ResponseEntity<LineModel>(entity2model(line), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody LineModel lineModel) {
		LOGGER.error(ctrlName + lineModel);
		service.save(model2entity(lineModel));
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{lineId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer lineId) {
		service.delete(lineId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	private LineModel entity2model(Line line) {
		LineModel e = new LineModel();
		e.setName(line.getName());
		e.setId(line.getId());
		e.setBuildYear(line.getBuildYear());
		e.setCapacity(line.getCapacity());
		e.setProductId(line.getProductId());
		e.setFactoryId(line.getFactoryId());
		e.setSpeed(line.getSpeed());
		e.setUnit(line.getUnit());
		return e;
	}

	private Line model2entity(LineModel lineModel) {
		Line e = new Line();
		e.setName(lineModel.getName());
		e.setId(lineModel.getId());
		e.setBuildYear(lineModel.getBuildYear());
		e.setCapacity(lineModel.getCapacity());
		e.setProductId(lineModel.getProductId());
		e.setFactoryId(lineModel.getFactoryId());
		e.setSpeed(lineModel.getSpeed());
		e.setUnit(lineModel.getUnit());
		return e;
	}

}
