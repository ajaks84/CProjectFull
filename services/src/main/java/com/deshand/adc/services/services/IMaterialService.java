package com.deshand.adc.services.services;

import java.util.List;

import com.deshand.adc.datamodel.Material;

public interface IMaterialService {
	
	Material get(Integer id);

	Material save(Material material);
	
	void delete(Integer id);

	List<Material> getAll();

}
