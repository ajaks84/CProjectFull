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

import com.deshand.adc.datamodel.Material;
import com.deshand.adc.services.services.IMaterialService;

public class MaterialServiceTest extends AbstractServiceTest {
	
	@Inject
	private  IMaterialService service;
	
	private Material material;
	private String name = "Cuprum Cord";
	private String unit = "m";
	Integer id=null;
	
	@Rule
	public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
	      LOGGER.info("Starting test: " + description.getMethodName());
	   }
	};

	private String entityName = "Material";

	@Before
	public void createTestObject() {
		drawLine();
		material = new Material();
		material.setName(name);
		material.setUnit(unit);
		material.setDeleted(false);
		id = service.save(material).getId();
	}
	
	//CREATE
	@Test
	public void testSaveInsertMethod() {
		Integer id;
		material.setId(null);
		Assert.notNull(id = service.save(material).getId(), entityName  + " object hasn't been created");
		LOGGER.info(entityName + " object has been created with id: " + id);
		service.delete(id);
	}
	
	//READ
	@Test
	public void testGetMethod() {
		Assert.isTrue(service.get(id).getId().equals(id),  "Cannot get created " + entityName + " object");
		LOGGER.info("Getting " + entityName + " object was succesfull, id is: " + id);	}
	
	//READ All
	@Test
	public void testGetAllMethod() {
		Integer size = 0;
		Integer id;
		Assert.notNull(size = service.getAll().size(), "Can't get list of Materials from DB");	
		LOGGER.info("There is (are): "+size+" Material objects in DB");
		LOGGER.info(material.toString());
		material.setId(null);
		LOGGER.info(material.toString());
		Assert.notNull(id = service.save(material).getId(), "Material object hasn't been created");
		Assert.isTrue(size+1 == service.getAll().size(), "Can't get list of Materials from DB");	
		LOGGER.info("There is (are): "+(size+1)+" Material objects in DB");
		service.delete(id);
	}
	
	//UPDATE
	@Test
	public void testSaveUpdateMethod() {
		String name = "Powder";
		LOGGER.info(material.toString());
		material.setName(name);
		Assert.notNull(service.save(material), entityName+" object hasn't been created");
		LOGGER.info(material.toString());
	}
	
	//DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		material.setId(null);
		Assert.notNull(id = service.save(material).getId(), entityName+" object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id),entityName + " object hasn't been deleted" );
	}
	
	@After
	public  void cleanUp(){
		service.delete(id);
		drawLine();
	}

}
