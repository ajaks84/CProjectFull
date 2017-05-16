package com.deshand.adc.dao.api;

import java.util.List;

import com.deshand.adc.datamodel.Material;

public interface IMaterialDao {
	
	Material get(Integer id);

	Material insert(Material material);

	Material update(Material material);
	
	void delete (Integer id);

	List<Material> getAll();

}
