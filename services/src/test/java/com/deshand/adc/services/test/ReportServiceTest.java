package com.deshand.adc.services.test;

import java.sql.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.util.Assert;

import com.deshand.adc.datamodel.Report;
import com.deshand.adc.services.services.IReportService;

public class ReportServiceTest extends AbstractServiceTest {

	@Inject
	private IReportService service;

	private Report report;
	private Integer id = null;
	private String entityName = "Report";
	private Integer input = 77634;
	private Integer output = input - 555;
	private Integer register = 50003;
	private Integer shift = 1;
	private Integer lineId = 2;
	private Long now = new java.util.Date().getTime();
	private Long ystrday = new java.util.Date().getTime() - 86400000;
	
	@Rule
	public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
	      LOGGER.info("Starting test: " + description.getMethodName());
	   }
	};

	@Before
	public void createTestObject() {
		drawLine();
		report = new Report();
		report.setInput(input);
		report.setOutput(output);
		report.setRegister(register);
		report.setShift(shift );
		report.setLineId(lineId);
		report.setDate(new Date(now));
		id = service.save(report).getId();
	}

	// CREATE
	@Test
	public void testSaveInsertMethod() {
		Integer id;
		report.setId(null);
		Assert.notNull(id = service.save(report).getId(), entityName + " object hasn't been created");
		LOGGER.info(entityName + " object has been created with id: " + id);
		service.delete(id);
	}

	// READ
	@Test
	public void testGetMethod() {
		Assert.isTrue(service.get(id).getId().equals(id), "Cannot get created " + entityName + " object");
		LOGGER.info("Getting " + entityName + " object was succesfull, id is: " + id);
	}

	// READ CONCRETE SHIFT
	@Test
	public void testGetSingleShiftReportMethod() {
		Integer id;
		Assert.notNull(id = service.getSingleShiftReport(lineId, new Date(now), shift).getId()
						,"Can't get object of " + entityName + " with given parameters");
		LOGGER.info("Getting " + entityName + " object was succesfull, id is: " + id);
		LOGGER.info(report.toString());
		service.delete(id);
	}

	// UPDATE
	@Test
	public void testSaveUpdateMethod() {
		LOGGER.info(report.toString());
		report.setDate(new Date(ystrday));
		service.save(report);
		Assert.isTrue(service.get(id).getDate().toString().equals(new Date(ystrday).toString()), entityName+" object hasn't been updated");
		LOGGER.info(report.toString());
	}

	// DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		report.setId(null);
		Assert.notNull(id = service.save(report).getId(), entityName + " object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id), entityName + " object hasn't been deleted");
		LOGGER.info(entityName +"object has been successfully deleted");
	}

	@After
	public void cleanUp() {
		service.delete(id);
		drawLine();
	}

}
