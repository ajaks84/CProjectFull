package com.deshand.adc.web.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deshand.adc.datamodel.Material;
import com.deshand.adc.services.services.IMaterialService;
import com.deshand.adc.web.model.MaterialModel;

@RestController
@RequestMapping("/material")
public class MaterialController {

	@Inject
	private IMaterialService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<MaterialModel> getById(@PathVariable("id") Integer id) {
		Material material = service.get(id);
		return new ResponseEntity<MaterialModel>(entity2model(material), HttpStatus.OK);
	}

	private MaterialModel entity2model(Material material) {
		MaterialModel e = new MaterialModel();
		e.setId(material.getId());
		e.setName(material.getName());
		e.setUnit(material.getUnit());
		e.setDeleted(material.getDeleted());
		return e;
	}
}