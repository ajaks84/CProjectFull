package com.deshand.adc.web.controller;

import java.sql.Date;
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

import com.deshand.adc.datamodel.Report;
import com.deshand.adc.services.services.IReportService;
import com.deshand.adc.web.model.ReportModel;

@RestController
@RequestMapping("/reports")
public class ReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

	private String ctrlName = this.getClass().getSimpleName();

	@Inject
	private IReportService service;

	@RequestMapping(value = "/by{lineId}/{reportDate}/{shift}", method = RequestMethod.GET)
	public ResponseEntity<ReportModel> getSingleShiftReport(@PathVariable("lineId") Integer lineId,
			@PathVariable("reportDate") Date reportDate, @PathVariable("shift") Integer shift) {
		Report report = service.getSingleShiftReport(lineId, reportDate, shift);
		return new ResponseEntity<ReportModel>(entity2model(report), HttpStatus.OK);
	}

	@RequestMapping(value = "/by{lineId}/{reportDate}", method = RequestMethod.GET)
	public ResponseEntity<List<ReportModel>> getShiftsByLineAndDate(@PathVariable("lineId") Integer lineId,
			@PathVariable("reportDate") Date reportDate) {
		List<Report> reports = service.getShiftsByLineAndDate(lineId, reportDate);
		LOGGER.error(ctrlName + reports);
		List<ReportModel> converted = new ArrayList<>();
		for (Report report : reports) {
			converted.add(entity2model(report));
		}
		return new ResponseEntity<List<ReportModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody ReportModel reportModel) {
		LOGGER.error(ctrlName + reportModel);
		service.save(model2entity(reportModel));
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{reportId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer reportId) {
		service.delete(reportId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private Report model2entity(ReportModel reportModel) {
		Report e = new Report();
		e.setId(reportModel.getId());
		e.setDate(reportModel.getDate());
		e.setInput(reportModel.getInput());
		e.setOutput(reportModel.getOutput());
		e.setRegister(reportModel.getRegister());
		e.setLineId(reportModel.getLineId());
		e.setShift(reportModel.getShift());
		return e;
	}

	private ReportModel entity2model(Report report) {
		ReportModel e = new ReportModel();
		e.setId(report.getId());
		e.setDate(report.getDate());
		e.setInput(report.getInput());
		e.setOutput(report.getOutput());
		e.setRegister(report.getRegister());
		e.setLineId(report.getLineId());
		e.setShift(report.getShift());
		return e;
	}

}
