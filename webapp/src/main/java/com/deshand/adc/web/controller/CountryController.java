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

import com.deshand.adc.datamodel.Country;
import com.deshand.adc.services.services.ICountryService;
import com.deshand.adc.web.model.CountryModel;

@RestController
@RequestMapping("/countries")
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	private String ctrlName = this.getClass().getSimpleName();

	@Inject
	private ICountryService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CountryModel>> getAll() {
		List<Country> all = service.getAll();
		LOGGER.error(ctrlName + all);
		List<CountryModel> converted = new ArrayList<>();
		for (Country country : all) {
			converted.add(entity2model(country));
		}
		return new ResponseEntity<List<CountryModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CountryModel> getById(@PathVariable Integer id) {
		Country country = service.get(id);
		return new ResponseEntity<CountryModel>(entity2model(country), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody CountryModel countryModel) {
		LOGGER.error(ctrlName + countryModel);
		service.save(model2entity(countryModel));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{countryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer countryId) {
		service.delete(countryId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private CountryModel entity2model(Country country) {
		CountryModel e = new CountryModel();
		e.setName(country.getName());
		e.setId(country.getId());
		return e;
	}

	private Country model2entity(CountryModel countryModel) {
		Country e = new Country();
		e.setName(countryModel.getName());
		e.setId(countryModel.getId());
		return e;
	}

}
