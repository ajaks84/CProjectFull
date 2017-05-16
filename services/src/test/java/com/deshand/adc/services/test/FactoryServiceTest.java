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

import com.deshand.adc.datamodel.Factory;
import com.deshand.adc.services.services.IFactoryService;

public class FactoryServiceTest extends AbstractServiceTest{
	
	@Inject
	private IFactoryService service;
	
	private Factory factory;
	private String name = "Stupino";
	private Integer countryId = 1;
	private String entityName = "Factory";
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
		factory = new Factory();
		factory.setName(name);
		factory.setCountryId(countryId);
		id = service.save(factory).getId();
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
		factory.setId(null);
		Assert.notNull(id = service.save(factory).getId(), entityName + " object hasn't been created");
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
		Assert.notNull(size = service.getAll().size(), "Can't get list of Materials from DB");	
		LOGGER.info("There is (are): "+size+" Material objects in DB");
		LOGGER.info(factory.toString());
		factory.setId(null);
		LOGGER.info(factory.toString());
		Assert.notNull(id = service.save(factory).getId(), "Material object hasn't been created");
		Assert.isTrue(size+1 == service.getAll().size(), "Can't get list of Materials from DB");	
		LOGGER.info("There is (are): "+(size+1)+" Material objects in DB");
		service.delete(id);
	}
	
	//UPDATE
	@Test
	public void testSaveUpdateMethod() {
		String name = "LZ-05";
		LOGGER.info(factory.toString());
		factory.setName(name);
		service.save(factory);
		Assert.isTrue(service.get(id).getName().toString().equals(name), entityName+" object hasn't been updated");
		LOGGER.info(factory.toString());
	}
	
	//DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		factory.setId(null);
		Assert.notNull(id = service.save(factory).getId(), entityName+" object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id),entityName + " object hasn't been deleted");
		LOGGER.info(entityName +" object has been successfully deleted");
	}
	
	@Test 
	public void testGetByCountryMethod() {
		String name = "Enem";
		Integer countryId = 2;
		
		LOGGER.info(factory.toString());
		
		factory.setCountryId(countryId);
		factory.setName(name);
		
		service.save(factory);
		LOGGER.info(factory.toString());
		Integer index= service.getByCountry(countryId).size();
		Assert.isTrue(service.getByCountry(countryId).get(index-1).getName().equals(name),
																name+" doesn't exist");
		LOGGER.info(entityName +" entry with factoryId="+countryId+" has been successfully received");
	}

}
