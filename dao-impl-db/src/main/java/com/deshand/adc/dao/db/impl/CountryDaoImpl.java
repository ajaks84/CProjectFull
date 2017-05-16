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

import com.deshand.adc.dao.api.ICountryDao;
import com.deshand.adc.dao.db.helper.ParentDao;
import com.deshand.adc.datamodel.Country;

@Repository
public class CountryDaoImpl extends ParentDao implements ICountryDao {
	
	Country country = new Country();

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Country get(Integer id) {
		return super.get(id, country);
	}

	@Override
	public Country insert(Country country) {

		final String INSERT_SQL = "insert into country (name) values(?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				setParams(ps,country);
				return ps;
			}
		}, keyHolder);

		country.setId(keyHolder.getKey().intValue());
		
		LOGGER.info("Country entry with id = " + country.getId() + " has been stored in DB");

		return country;

	}

	public Country update(Country country) {
		final String INSERT_SQL = "update country set name=? where id="
				+ country.getId();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				setParams(ps,country);
				return ps;
			}
		});

		LOGGER.info("Country entry with id = " + country.getId() + " has been updated");

		return country;

	}

	@Override
	public List<Country> getAll() {
		return super.getAll(country);
	}

	@Override
	public void delete(Integer id) {
		super.delete(id, country);

	}
	
	private PreparedStatement setParams(PreparedStatement ps, Country country) throws SQLException{
		ps.setString(1, country.getName());
		return ps;

	}
	
	
	public static void main(String[] args) {
		
		CountryDaoImpl c = new CountryDaoImpl();
		c.helper(); 
	}
	
	private void helper(){
		
		Country cons = new Country();
		super.buildUpdateQueryStr(cons, 1);
		
		
	}



}
