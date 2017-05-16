package com.deshand.adc.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.IProductDao;
import com.deshand.adc.datamodel.Product;
import com.deshand.adc.services.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Inject
	private IProductDao productDao;

	@Override
	public Product get(Integer id) {
		return productDao.get(id);
	}

	@Override
	public Product save(Product product) {
		if (product.getId() == null) {
			productDao.insert(product);
		} else {
			productDao.update(product);
		}
		return product;
	}

	@Override
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);

	}

}
