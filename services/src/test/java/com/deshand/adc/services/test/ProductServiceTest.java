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

import com.deshand.adc.datamodel.Product;
import com.deshand.adc.services.services.IProductService;

public class ProductServiceTest extends AbstractServiceTest {

	@Inject
	private IProductService service;

	Product product;
	private String name = "3-piece-can";
	private Integer id = null;
	String entityName = "Product";
	
	@Rule
	public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
	      LOGGER.info("Starting test: " + description.getMethodName());
	   }
	};

	@Before
	public void createTestObject() {
		drawLine();
		product = new Product();
		product.setName(name);
		product.setGroup(1);
		id = service.save(product).getId();
	}

	// CREATE
	@Test
	public void testSaveInsertMethod() {
		Integer id;
		product.setId(null);
		Assert.notNull(id = service.save(product).getId(), entityName + " object hasn't been created");
		LOGGER.info(entityName + " object has been created with id: " + id);
		service.delete(id);
	}

	// READ
	@Test
	public void testGetMethod() {
		Assert.isTrue(service.get(id).getId().equals(id), "Cannot get created " + entityName + " object");
		LOGGER.info("Getting " + entityName + " object was succesfull, id is: " + id);
	}

	// READ All
	@Test
	public void testGetAllMethod() {
		Integer size = 0;
		Integer id;
		Assert.notNull(size = service.getAll().size(), "Can't get list of " + entityName + "'s from DB");
		LOGGER.info("There is (are): " + size +" "+ entityName + " objects in DB");
		LOGGER.info(product.toString());
		product.setId(null);
		LOGGER.info(product.toString());
		Assert.notNull(id = service.save(product).getId(), entityName+" object hasn't been created");
		Assert.isTrue(size + 1 == service.getAll().size(), "Can't get list of "+entityName+"'s from DB");
		LOGGER.info("There is (are): " + (size + 1) +" "+ entityName+" objects in DB");
		service.delete(id);
	}

	// UPDATE
	@Test
	public void testSaveUpdateMethod() {
		String name = "Hansa can";
		LOGGER.info(product.toString());
		product.setName(name);
		Assert.notNull(service.save(product), "Material object hasn't been created");
		LOGGER.info(product.toString());
	}

	// DELETE
	@Test
	public void testDeleteMethod() {
		Integer id;
		product.setId(null);
		Assert.notNull(id = service.save(product).getId(), entityName+" object hasn't been created");
		service.delete(id);
		Assert.isNull(service.get(id), entityName+" object hasn't been deleted");
	}

	@After
	public void cleanUp() {
		service.delete(id);
		drawLine();
	}

}
