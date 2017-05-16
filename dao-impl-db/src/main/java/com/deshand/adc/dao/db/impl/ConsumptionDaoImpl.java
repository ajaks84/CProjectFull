package com.deshand.adc.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IConsumptionDao;
import com.deshand.adc.dao.db.helper.ParentDao;
import com.deshand.adc.dao.db.mapper.ConsumptionWithMaterialMapper;
import com.deshand.adc.datamodel.Consumption;
import com.deshand.adc.datamodel.ConsumptionWithMaterial;

@Repository
public class ConsumptionDaoImpl extends ParentDao implements IConsumptionDao {

	Consumption cons = new Consumption();
	private String entityName = cons.getClass().getSimpleName();

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private PreparedStatement setParams(PreparedStatement ps, Consumption consumption) throws SQLException{
		ps.setLong(1, consumption.getReportId());
		ps.setLong(2, consumption.getMaterialId());
		ps.setDouble(3, consumption.getQuantity());
		return ps;

	}

	@Override
	public Consumption get(Integer id) {
		return super.get(id, cons);
	}

	@Override
	public Consumption insert(Consumption cons) {

		final String INSERT_SQL = "insert into "+entityName+" (report_id,material_id,quantity)values(?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				setParams(ps,cons);
				return ps;
			}
		}, keyHolder);

		cons.setId(keyHolder.getKey().intValue());
		
		LOGGER.info(entityName+" entry with id = " + cons.getId() + " has been inserted in DB");
		
		return cons;

	}

	@Override
	public Consumption update(Consumption cons) {
		
		final String INSERT_SQL = "update "+entityName+" set report_id=?, material_id=?, quantity=? where id="
				+ cons.getId();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				setParams(ps,cons);
				return ps;
			}
		});
	
		LOGGER.info(entityName+" entry with id = " + cons.getId() + " has been updated");

		return cons;

	}

	@Override
	public void delete(Integer id) {
		super.delete(id, cons);

	}
	
	@Override
	public List<Consumption> getAll() {
		return super.getAll(cons);
	}

	@Override
	public List<ConsumptionWithMaterial> getByReportId(Integer reportId) {
		List<ConsumptionWithMaterial> rs = jdbcTemplate.query("SELECT cons.id, material_id, report_id, quantity, name, unit, deleted FROM consumption as cons "
				+ "right join material mat on (cons.material_id=mat.id) where cons.report_id=" + reportId,
				new ConsumptionWithMaterialMapper());
		return rs;
	}

}
