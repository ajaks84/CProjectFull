package com.deshand.adc.dao.xml.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.api.IReportDao;
import com.deshand.adc.datamodel.Report;

@Repository
public class ReportDaoXmlImpl implements IReportDao{

	@Override
	public Report get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report insert(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report update(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Report getSingleShiftReport(Integer lineId, Date reportDate, Integer shift) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> getShiftsByLineAndDate(Integer lineId, Date reportDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
