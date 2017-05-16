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

import com.deshand.adc.dao.api.IProductDao;
import com.deshand.adc.datamodel.Product;

@Repository
public class ProductDaoImpl implements IProductDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Product get(Integer id) {
		try {
			return jdbcTemplate.queryForObject("select * from product where id = ? ", new Object[] { id },
					new BeanPropertyRowMapper<Product>(Product.class));
		} catch (Exception e) {
			LOGGER.error("Get Product by ID " + id + " - ERROR");
		}
		return null;
	}

	@Override
	public Product insert(Product product) {
		
		LOGGER.info(product.toString());

		final String INSERT_SQL = "insert into product (name, groupe) values(?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, product.getName());
				ps.setInt(2, product.getGroupe());
				return ps;
			}
		}, keyHolder);

		product.setId(keyHolder.getKey().intValue());

		LOGGER.info("Product entry with id = " + product.getId() + " has been stored in DB");

		return product;
	}

	@Override
	public Product update(Product product) {

		final String INSERT_SQL = "update product set name=?, groupe=? where id=" + product.getId();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				ps.setString(1, product.getName());
				ps.setLong(2, product.getGroupe());
				return ps;
			}
		});
		LOGGER.info("Product entry with id = " + product.getId() + " has been updated");

		return product;
	}

	@Override
	public List<Product> getAll() {
		List<Product> rs = jdbcTemplate.query("select * from product",
				new BeanPropertyRowMapper<Product>(Product.class));
		return rs;
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from product where id=" + id);
		LOGGER.info("Product entry with id = " + id + " has been deleted from DB");
	}

}
