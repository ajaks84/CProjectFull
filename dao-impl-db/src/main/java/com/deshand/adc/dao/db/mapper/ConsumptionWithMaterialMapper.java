package com.deshand.adc.dao.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;
import com.deshand.adc.datamodel.Material;

public final class ConsumptionWithMaterialMapper implements RowMapper<ConsumptionWithMaterial> {
	@Override
	public ConsumptionWithMaterial mapRow(ResultSet rs, int rowNum) throws SQLException {

//		This piece of code has been working
		
//		ConsumptionWithMaterial consWithMat = new ConsumptionWithMaterial();
//		consWithMat.setName(rs.getString("name"));
//		consWithMat.setUnit(rs.getString("unit"));
//		consWithMat.setQuantity(rs.getDouble("quantity"));
//		consWithMat.setDeleted(rs.getBoolean("deleted"));
//		return consWithMat;
		
		Consumption cons = new Consumption();
		cons.setId(rs.getInt("id"));
		cons.setMaterialId(rs.getInt("material_id"));
		cons.setQuantity(rs.getDouble("quantity"));
		cons.setReportId(rs.getInt("report_id"));
		
		Material mat = new Material();
//		mat.setId(rs.getInt("id"));
		mat.setDeleted(rs.getBoolean("deleted"));
		mat.setName(rs.getString("name"));
		mat.setUnit(rs.getString("unit"));
		
		
		
		ConsumptionWithMaterial consWithMat = new ConsumptionWithMaterial();
		consWithMat.setCons(cons);
		consWithMat.setMat(mat);
		
		return consWithMat;
	}
}
