package com.deshand.adc.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.ILineDao;
import com.deshand.adc.dao.db.helper.ParentDao;
import com.deshand.adc.datamodel.Line;

@Repository
public class LineDaoImpl extends ParentDao implements ILineDao {
	
	Line line = new Line();
	
	@Override
	public Line get(Integer id) {
		return super.get(id, line);
	}

	@Override
	public Line insert(Line line) {

		final String INSERT_SQL = "insert into line(name,product_id,speed,unit,capacity,build_year,factory_id)values(?,?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });

				setParams(ps,line);

				return ps;
			}
		}, keyHolder);

		line.setId(keyHolder.getKey().intValue());

		LOGGER.info("Line entry with id = " + line.getId() + " has been stored in DB");

		return line;

	}

	public Line update(Line line) {
		
		//final String INSERT_SQL = super.buildUpdateQueryStr(line, line.getId());
		
		final String INSERT_SQL = "update line set name=?,product_id=?,speed=?,unit=?,capacity=?,build_year=?,factory_id=? where id="
				+ line.getId();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				setParams(ps,line);
				return ps;
			}
		});

		LOGGER.info("Report entry with id = " + line.getId() + " has been updated");

		return line;

	}

	@Override
	public List<Line> getLineByFactory(Integer factoryId) {

		List<Line> rs = jdbcTemplate.query("select * from line where factory_id=" + factoryId,
				new BeanPropertyRowMapper<Line>(Line.class));
		return rs;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id, line);

	}

	@Override
	public List<Line> getAll() {
		return super.getAll(line);
	}
	
	private PreparedStatement setParams(PreparedStatement ps, Line line) throws SQLException{
		ps.setString(1, line.getName());
		ps.setLong(2, line.getProductId());
		ps.setLong(3, line.getSpeed());
		ps.setString(4, line.getUnit());
		ps.setLong(5, line.getCapacity());
		ps.setLong(6, line.getBuildYear());
		ps.setLong(7, line.getFactoryId());
		return ps;

	}
	
//	 public static void main(String[] args) {
//			
//		 LineDaoImpl c = new LineDaoImpl();
//	 c.helper();
//	 }
//	
//	 private void helper(){
//	
//		 Line cons = new Line();
//	 super.buildUpdateQueryStr(cons,2);
//		try {
//			super.convert(cons);
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	
	
//	 }


}
