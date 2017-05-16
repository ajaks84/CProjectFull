package com.deshand.adc.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IProductDao;
import com.deshand.adc.dao.xml.wrapper.XmlModelWrapper;
import com.deshand.adc.datamodel.Product;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class ProductDaoXmlImpl implements IProductDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoXmlImpl.class);

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	@SuppressWarnings("unchecked")
	@Override
	public Product insert(Product product) {

		File file = getFile();

		XmlModelWrapper<Integer, Product> wrapper = (XmlModelWrapper<Integer, Product>) xstream.fromXML(file);
		List<Product> products = wrapper.getRows();
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		product.setId(newId);
		products.add(product);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);
		LOGGER.info("Product entry with id = " + product.getId() + " has been stored in DB");
		return product;

	}
	
	 @Override
	public Product update(Product product) {
	 File file = getFile();
	
	 @SuppressWarnings("unchecked")
	XmlModelWrapper<Integer, Product> wrapper = (XmlModelWrapper<Integer, Product>)
	 xstream.fromXML(file);
	 List<Product> books = wrapper.getRows();
	 for (Product productItem : books) {
	 if (productItem.getId().equals(product.getId())) {
		 productItem.setName(product.getName());
		 productItem.setGroup(product.getGroupe());
	 break;
	 }
	 }
	 writeNewData(file, wrapper);
	return product;
	 }
	
	 @SuppressWarnings("unchecked")
	@Override
	 public List<Product> getAll() {
	 File file = getFile();
	
	 XmlModelWrapper<Integer, Product> wrapper = (XmlModelWrapper<Integer, Product>)
	 xstream.fromXML(file);
	 return wrapper.getRows();
		 //throw new NotSupportedMethodException();
	 }
	
	 @SuppressWarnings("unchecked")
	@Override
	 public void delete(Integer id) {
		 
	 File file = getFile();
	
	 XmlModelWrapper<Integer, Product> wrapper = (XmlModelWrapper<Integer, Product>)
	 xstream.fromXML(file);
	 List<Product> products = wrapper.getRows();
	 Product found = null;
	 for (Product product : products) {
	 if (product.getId().equals(id)) {
	 found = product;
	 break;
	 }
	 }
	 if (found != null) {
	 products.remove(found);
	 writeNewData(file, wrapper);
	 }
	 
	 LOGGER.info("Product entry with id = " + id + " has been deleted from DB");
	 
	 }

	private void writeNewData(File file, @SuppressWarnings("rawtypes") XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile() {
		File file = new File(rootFolder + "product.xml");
		return file;
	}

	@Override
	public Product get(Integer id) {
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Integer, Product> wrapper = (XmlModelWrapper<Integer, Product>) xstream.fromXML(file);
		List<Product> products = wrapper.getRows();
		for (Product product : products) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		LOGGER.error("Get Product by ID " + id + " - ERROR");
		return null;
	}

	

}
