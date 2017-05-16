package com.deshand.adc.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IFactoryDao;
import com.deshand.adc.datamodel.Factory;

@Repository
public class FactoryDaoImpl implements IFactoryDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FactoryDaoImpl.class);


	@Override
	public Factory get(Integer id) {
		try {
			return jdbcTemplate.queryForObject("select * from factory where id = ? ", new Object[] { id },
					new BeanPropertyRowMapper<Factory>(Factory.class));
		} catch (Exception e) {
			LOGGER.error("Get Factory by ID " + id + " - ERROR");
		}
		return null;
	}

	@Override
	public Factory insert(Factory factory) {

		final String INSERT_SQL = "insert into factory (name, country_id) values(?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, factory.getName());
				ps.setLong(2, factory.getCountryId());
				return ps;
			}
		}, keyHolder);

		factory.setId(keyHolder.getKey().intValue());
		
		LOGGER.info("Factory entry with id = " + factory.getId() + " has been stored in DB");

		return factory;

	}
	
	@Override
	public Factory update(Factory factory) {
		
		final String INSERT_SQL = "update factory set name=?,country_id=? where id="+factory.getId();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				ps.setString(1, factory.getName());
				ps.setLong(2, factory.getCountryId());
				
				return ps;
			}
		});

		LOGGER.info("Report entry with id = " + factory.getId() + " has been updated");

		return factory;


	}
	
	@Override
	public List<Factory> getAll() {
		List<Factory> rs = jdbcTemplate.query("select * from factory ", 
				new BeanPropertyRowMapper<Factory>(Factory.class));
		return rs;
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from factory where id=" + id);
		LOGGER.info("Factory entry with id = " + id + " has been deleted from DB");

	}
	
	@Override
	public List<Factory> getByCountry(Integer countryId) {
		List<Factory> rs = jdbcTemplate.query("select * from factory where country_id=" + countryId, 
													new BeanPropertyRowMapper<Factory>(Factory.class));
		
		LOGGER.info("Factory entry with countryId = " + countryId + " has been successfully received from DB");
		return rs;
	}

}
