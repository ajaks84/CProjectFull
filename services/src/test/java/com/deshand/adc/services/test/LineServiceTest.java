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

import com.deshand.adc.datamodel.Line;
import com.deshand.adc.services.services.ILineService;

public class LineServiceTest extends AbstractServiceTest {
	

	@Inject
	private ILineService service;
	
	private Line line;
	private String name = "LZ-01";
	private Integer productId = 1;
	private Integer speed = 200;
	private Integer capacity = 1000000;
	private Integer buildYear = 1993;
	private Integer factoryId = 1;
	private String unit = "can per minute";
	private String entityName = "Line";
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
		line = new Line();
		line.setName(name);
		line.setProductId(productId);
		line.setSpeed(speed);
		line.setUnit(unit);
		line.setCapacity(capacity);
		line.setBuildYear(buildYear);
		line.setFactoryId(factoryId);
		id = service.save(line).getId();
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
		line.setId(null);
		Assert.notNull(id = service.save(line).getId(), entityName + " object hasn't been created");
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
		LOGGER.info(line.toString());
		line.setId(null);
		LOGGER.info(line.toString());
		Assert.notNull(id = service.save(line).getId(), "Material object hasn't been created");
		Assert.isTrue(size+1 == service.getAll().size(), "Can't get list of Materials from DB");	
		LOGGER.info("There is (are): "+(size+1)+" Material objects in DB");
		service.delete(id);
	}
	
	//UPDATE
	@Test
	public void testSaveUpdateMethod() {
		String name = "LZ-05";
		LOGGER.info(line.toString());
		line.setName(name);
		service.save(line);
		Assert.isTrue(service.get(id).getName().toString().equals(name), entityName+" object hasn't been updated");
		LOGGER.info(line.toString());
	}
	
	//DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		line.setId(null);
		Assert.notNull(id = service.save(line).getId(), entityName+" object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id),entityName + " object hasn't been deleted");
		LOGGER.info(entityName +" object has been successfully deleted");
	}

	@Test 
	public void testGetLineByFactoryMethod() {
		String name = "LN-02";
		Integer factoryId = 2;
		
		line.setFactoryId(factoryId);
		line.setName(name);
		
		service.save(line);
		Integer index= service.getLineByFactory(factoryId).size();
		Assert.isTrue(service.getLineByFactory(factoryId).get(index-1).getName().equalsIgnoreCase(name),
																		name+" doesn't exist");
		LOGGER.info(entityName +" entry with factoryId="+factoryId+" has been successfully received");
	}

}
