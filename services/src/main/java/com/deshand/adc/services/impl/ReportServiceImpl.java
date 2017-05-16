package com.deshand.adc.services.impl;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.api.IReportDao;
import com.deshand.adc.datamodel.Report;
import com.deshand.adc.services.services.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

    @Inject
    private IReportDao reportDao;

    public Report save(Report report) {
        if (report.getId() == null) {
            reportDao.insert(report);
        } else {
        	reportDao.update(report);
        }
		return report;
    }
    
    @Override
    public void delete(Integer id) {
    	reportDao.delete(id);

    }

    @Override
	public Report get(Integer id) {
		return reportDao.get(id);
	}
	
    @Override
	public Report getSingleShiftReport(Integer lineId, Date reportDate, Integer shift) {
		return reportDao.getSingleShiftReport(lineId,reportDate,shift);
	}

	@Override
	public List<Report> getShiftsByLineAndDate(Integer lineId, Date reportDate) {
		return reportDao.getShiftsByLineAndDate(lineId,reportDate);
	}

}
