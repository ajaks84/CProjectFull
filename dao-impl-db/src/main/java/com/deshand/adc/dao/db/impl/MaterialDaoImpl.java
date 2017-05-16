package com.deshand.adc.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IMaterialDao;
import com.deshand.adc.dao.db.helper.ParentDao;
import com.deshand.adc.datamodel.Material;

@Repository
public class MaterialDaoImpl extends ParentDao implements IMaterialDao {

	private Material material = new Material();
	private String entityName = getEntityName(material);

	@Override
	public Material get(Integer id) {
		return super.get(id, material);
	}

	@Override
	public Material insert(Material material) {

		final String INSERT_SQL = "insert into material (name, unit, deleted) values(?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, material.getName());
				ps.setString(2, material.getUnit());
				ps.setBoolean(3, material.getDeleted());
				return ps;
			}
		}, keyHolder);

		material.setId(keyHolder.getKey().intValue());

		LOGGER.info(entityName + " entry with id = " + material.getId() + " has been stored in DB");

		return material;
	}

	@Override
	public Material update(Material material) {

		final String INSERT_SQL = super.buildUpdateQueryStr(material, material.getId());

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				setParams(ps, material);
				return ps;
			}
		});

		LOGGER.info(entityName + " entry with id = " + material.getId() + " has been updated");

		return material;
	}

	// @Override
	// public Material update(Material material) {
	//
	// super.update(material, material.getId());
	//
	// jdbcTemplate.update(new PreparedStatementCreator() {
	// @Override
	// public PreparedStatement createPreparedStatement(Connection connection)
	// throws SQLException {
	// PreparedStatement ps = connection.prepareStatement("sts", new String[]
	// {});
	// setParams(ps, material);
	// return ps;
	// }
	// });
	//
	// LOGGER.info(entityName + " entry with id = " + material.getId() + " has
	// been updated");
	//
	// return material;
	// }

	@Override
	public List<Material> getAll() {
		return super.getAll(material);
	}

	@Override
	public void delete(Integer id) {
		super.delete(id, material);

	}

	private PreparedStatement setParams(PreparedStatement ps, Material material) throws SQLException {
		ps.setString(1, material.getName());
		ps.setString(2, material.getUnit());
		ps.setBoolean(3, material.getDeleted());
		return ps;
	}

//	public static void main(String[] args) {
//
//		MaterialDaoImpl c = new MaterialDaoImpl();
//		c.helper();
//	}
//
//	private void helper() {
//
//		Material cons = new Material();
//		super.buildUpdateQueryStr(cons, 2);
//		try {
//			super.convert(cons);
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
