package com.deshand.adc.services.services;

import java.sql.Date;
import java.util.List;

import com.deshand.adc.datamodel.Report;

public interface IReportService {

    Report get(Integer id);

    Report save(Report report);

    void delete(Integer id);

	Report getSingleShiftReport(Integer lineId, Date reportDate, Integer shift);

	List<Report> getShiftsByLineAndDate(Integer lineId, Date reportDate);
}
