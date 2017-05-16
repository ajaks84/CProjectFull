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

import com.deshand.adc.datamodel.Country;
import com.deshand.adc.services.services.ICountryService;

public class CountryServiceTest extends AbstractServiceTest {

	@Inject
	private ICountryService service;
	
	private Country country;
	private String name = "Jamaica";
	private String entityName = "Country";
	Integer id=null;
	
	@Rule
	public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
	      LOGGER.info("Starting test: " + description.getMethodName());
	   }
	};

	@Before
	public void createTestObject() {
		drawLine();
		country = new Country();
		country.setName(name);
		id = service.save(country).getId();
	}
	
	@After
	public  void cleanUp(){
		service.delete(id);
		drawLine();
	}
	
	//CREATE
	@Test
	public void testSaveInsertMethod() {
		Integer id;
		country.setId(null);
		Assert.notNull(id = service.save(country).getId(), entityName + " object hasn't been created");
		LOGGER.info(entityName + " object has been created with id: " + id);
		service.delete(id);
	}
	
	//READ
	@Test
	public void testGetMethod() {
		Assert.isTrue(service.get(id).getId().equals(id), "Cannot get created " + entityName + " object");
		LOGGER.info("Getting " + entityName + " object was succesfull, id is: " + id);
	}
	
	//READ All
	@Test
	public void testGetAllMethod() {
		Integer size = 0;
		Integer id;
		Assert.notNull(size = service.getAll().size(), "Can't get list of"+entityName+"s from DB");	
		LOGGER.info("There is (are): "+size+" "+entityName+" objects in DB");
		LOGGER.info(country.toString());
		country.setId(null);
		LOGGER.info(country.toString());
		Assert.notNull(id = service.save(country).getId(), entityName+" object hasn't been created");
		Assert.isTrue(size+1 == service.getAll().size(), "Can't get list of"+entityName+"s from DB");	
		LOGGER.info("There is (are): "+(size+1)+" "+entityName+" objects in DB");
		service.delete(id);
	}
	
	//UPDATE
	@Test
	public void testSaveUpdateMethod() {
		String name = "Peru";
		LOGGER.info(country.toString());
		country.setName(name);
		service.save(country);
		Assert.isTrue(service.get(id).getName().toString().equals(name), entityName+" object hasn't been updated");
		LOGGER.info(country.toString());
	}
	
	//DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		country.setId(null);
		Assert.notNull(id = service.save(country).getId(), entityName+" object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id),entityName + " object hasn't been deleted");
		LOGGER.info(entityName +" object has been successfully deleted");
	}
	
}
