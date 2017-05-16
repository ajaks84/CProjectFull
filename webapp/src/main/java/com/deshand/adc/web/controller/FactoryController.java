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

import com.deshand.adc.datamodel.Factory;
import com.deshand.adc.services.services.IFactoryService;
import com.deshand.adc.web.model.FactoryModel;

@RestController
@RequestMapping("/factories")
public class FactoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FactoryController.class);

	private String ctrlName = this.getClass().getSimpleName();

	@Inject
	private IFactoryService service;

	@RequestMapping(value = "/by{countryId}", method = RequestMethod.GET)
	public ResponseEntity<List<FactoryModel>> getByCountry(@PathVariable("countryId") Integer countryId) {
		List<Factory> factories = service.getByCountry(countryId);
		LOGGER.error(ctrlName + factories);
		List<FactoryModel> converted = new ArrayList<>();
		for (Factory factory : factories) {
			converted.add(entity2model(factory));
		}
		return new ResponseEntity<List<FactoryModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FactoryModel>> getAll() {
		List<Factory> all = service.getAll();
		LOGGER.error(ctrlName + all);
		List<FactoryModel> converted = new ArrayList<>();
		for (Factory country : all) {
			converted.add(entity2model(country));
		}
		return new ResponseEntity<List<FactoryModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FactoryModel> getById(@PathVariable Integer id) {
		Factory factory = service.get(id);
		return new ResponseEntity<FactoryModel>(entity2model(factory), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody FactoryModel factoryModel) {
		LOGGER.error(ctrlName + factoryModel);
		service.save(model2entity(factoryModel));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{factoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer factoryId) {
		service.delete(factoryId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	private FactoryModel entity2model(Factory factory) {
		FactoryModel e = new FactoryModel();
		e.setName(factory.getName());
		e.setId(factory.getId());
		e.setCountry_id(factory.getCountryId());
		return e;
	}

	private Factory model2entity(FactoryModel factoryModel) {
		Factory e = new Factory();
		e.setName(factoryModel.getName());
		e.setId(factoryModel.getId());
		e.setCountryId(factoryModel.getCountry_id());
		return e;
	}

}
