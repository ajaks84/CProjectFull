package com.deshand.adc.services.test;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.util.Assert;

import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.services.services.IConsumptionService;

public class ConsumptionServiceTest extends AbstractServiceTest {

	@Inject
	private IConsumptionService service;

	private Consumption cons;
	private Integer reportId = 1;
	private Integer materialId = 1;
	private Double quantity = 4.6;
	private String entityName = "Consumption";
	private Integer id = null;

	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			LOGGER.info("Starting test: " + description.getMethodName());
		}
	};

	@Before
	public void createTestObject() {
		drawLine();
		cons = new Consumption();
		cons.setMaterialId(materialId);
		cons.setQuantity(quantity);
		cons.setReportId(reportId);
		id = service.save(cons).getId();
	}

	@After
	public void cleanUp() {
		service.delete(id);
		drawLine();
	}

	// CREATE
	@Test
	public void testSaveInsertMethod() {
		Integer id;
		cons.setId(null);
		Assert.notNull(id = service.save(cons).getId(), entityName + " object hasn't been created");
		LOGGER.info(entityName + " object has been created with id: " + id);
		service.delete(id);
	}

	// READ
	@Test
	public void testGetMethod() {
		Assert.isTrue(service.get(id).getId().equals(id), "Cannot get created " + entityName + " object");
		LOGGER.info("Getting " + entityName + " object was succesfull, id is: " + id);
	}
	
	// READ BY REPORT ID
	@Test
	public void testGetByReportId() {
		Assert.notNull(service.getByReportId(reportId), "Consumption object has been lost");
		LOGGER.info(entityName + " with report_id = "+ reportId+" has been successfully obtained");
	}

	// READ All
	@Test
	public void testGetAllMethod() {
		Integer size = 0;
		Integer id;
		Assert.notNull(size = service.getAll().size(), "Can't get list of" + entityName + "s from DB");
		LOGGER.info("There is (are): " + size + " " + entityName + " objects in DB");
		LOGGER.info(cons.toString());
		cons.setId(null);
		LOGGER.info(cons.toString());
		Assert.notNull(id = service.save(cons).getId(), entityName + " object hasn't been created");
		Assert.isTrue(size + 1 == service.getAll().size(), "Can't get list of" + entityName + "s from DB");
		LOGGER.info("There is (are): " + (size+1)+ " " + entityName + " objects in DB");
		service.delete(id);
	}

	// UPDATE
	@Test
	public void testSaveUpdateMethod() {
		Double quantity = 100.2;
		LOGGER.info(cons.toString());
		cons.setQuantity(quantity);
		service.save(cons);
		Assert.isTrue(service.get(id).getQuantity().equals(quantity), entityName + " object hasn't been updated");
		LOGGER.info(cons.toString());
	}

	// DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		cons.setId(null);
		Assert.notNull(id = service.save(cons).getId(), entityName + " object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id), entityName + " object hasn't been deleted");
		LOGGER.info(entityName + " object has been successfully deleted");
	}

}
