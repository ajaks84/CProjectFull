package com.deshand.adc.dao.api;

import java.util.List;

import com.deshand.adc.datamodel.Product;

public interface IProductDao {
	
	Product get(Integer id);

	Product insert(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> getAll();
}
