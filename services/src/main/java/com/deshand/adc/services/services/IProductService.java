package com.deshand.adc.services.services;

import java.util.List;

import com.deshand.adc.datamodel.Product;

public interface IProductService {
	
	Product get(Integer id);

	Product save(Product product);

    List<Product> getAll();

    void delete(Integer id);

}
