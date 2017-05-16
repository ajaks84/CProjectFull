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

import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;
import com.deshand.adc.services.services.IConsumptionService;
import com.deshand.adc.web.model.ConsumptionWithMateialModel;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionController.class);

	private String ctrlName = this.getClass().getSimpleName();

	@Inject
	private IConsumptionService service;

	@RequestMapping(value = "/by{reportId}", method = RequestMethod.GET)
	public ResponseEntity<List<ConsumptionWithMateialModel>> getByReportId(@PathVariable("reportId") Integer reportId) {
		List<ConsumptionWithMaterial> all = service.getByReportId(reportId);
		LOGGER.info(ctrlName + all);
		List<ConsumptionWithMateialModel> converted = new ArrayList<>();
		for (ConsumptionWithMaterial cons : all) {
			converted.add(entity2model(cons));
		}
		return new ResponseEntity<List<ConsumptionWithMateialModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Consumption consModel) {
		LOGGER.error(ctrlName + consModel);
		service.save(model2entity(consModel));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{consId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer consId) {
		service.delete(consId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private ConsumptionWithMateialModel entity2model(ConsumptionWithMaterial cons) {
		ConsumptionWithMateialModel e = new ConsumptionWithMateialModel();

		// e.setDeleted(cons.getDeleted());
		// e.setName(cons.getName());
		// e.setQuantity(cons.getQuantity());
		// e.setUnit(cons.getUnit());

		e.setDeleted(cons.getMat().getDeleted());
		e.setName(cons.getMat().getName());
		e.setQuantity(cons.getCons().getQuantity());
		e.setUnit(cons.getMat().getUnit());
		e.setId(cons.getCons().getId());
		e.setReportId(cons.getCons().getReportId());
		e.setMaterialId(cons.getCons().getMaterialId());

		return e;
	}

	private Consumption model2entity(Consumption consModel) {
		Consumption e = new Consumption();
		e.setId(consModel.getId());
		e.setMaterialId(consModel.getMaterialId());
		e.setQuantity(consModel.getQuantity());
		e.setReportId(consModel.getReportId());
		
		return e;
	}
}
