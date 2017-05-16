package com.deshand.adc.dao.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IReportDao;
import com.deshand.adc.dao.db.helper.ParentDao;
import com.deshand.adc.datamodel.Report;

@Repository
public class ReportDaoImpl extends ParentDao implements IReportDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	Report report = new Report();

	@Override
	public Report get(Integer id) {
		return super.get(id, report);
	}

	@Override
	public Report insert(Report report) {

		final String INSERT_SQL = "insert into report (date,shift,register,input,output,line_id)values(?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				setParams(ps, report);
				return ps;
			}
		}, keyHolder);

		report.setId(keyHolder.getKey().intValue());

		LOGGER.info("Report entry with id = " + report.getId() + " has been stored in DB");

		return report;

	}

	@Override
	public Report update(Report report) {

		System.out.println(report);

		final String INSERT_SQL = super.buildUpdateQueryStr(report, report.getId());

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				setParams(ps, report);
				return ps;
			}
		});

		LOGGER.info("Report entry with id = " + report.getId() + " has been updated");

		return report;

	}

	@Override
	public void delete(Integer id) {
		super.delete(id, report);

	}

	@Override
	public Report getSingleShiftReport(Integer lineId, Date reportDate, Integer shift) {
		try {
			return jdbcTemplate.queryForObject("select * from report where line_id = ? and date = ? and shift = ?",
					new Object[] { lineId, reportDate, shift }, new BeanPropertyRowMapper<Report>(Report.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("Cannot access data with following parameters: lineId " + lineId + " reportDate " + reportDate
					+ " shift " + shift);
			return null;
		}
	}

	@Override
	public List<Report> getShiftsByLineAndDate(Integer lineId, Date reportDate) {

		return jdbcTemplate.query("select * from report where line_id = ? and date = ?",
				new Object[] { lineId, reportDate }, new BeanPropertyRowMapper<Report>(Report.class));

	}

	private PreparedStatement setParams(PreparedStatement ps, Report report) throws SQLException {
		ps.setDate(1, report.getDate());
		ps.setLong(2, report.getShift());
		ps.setLong(3, report.getRegister());
		ps.setLong(4, report.getInput());
		ps.setLong(5, report.getOutput());
		ps.setLong(6, report.getLineId());
		return ps;

	}

}
