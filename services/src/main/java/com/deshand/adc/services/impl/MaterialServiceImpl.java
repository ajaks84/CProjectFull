package com.deshand.adc.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.IMaterialDao;
import com.deshand.adc.datamodel.Material;
import com.deshand.adc.services.services.IMaterialService;


@Service
public class MaterialServiceImpl implements IMaterialService{
	
	@Inject
    private IMaterialDao materialDao;

	@Override
	public Material get(Integer id) {
		return materialDao.get(id);
	}

	@Override
	public Material save(Material material) {
		if (material.getId() == null) {
			materialDao.insert(material);
        } else {
        	materialDao.update(material);
        }
		return material;
	}

	@Override
	public List<Material> getAll() {
		return materialDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		materialDao.delete(id);
		
	}

}
