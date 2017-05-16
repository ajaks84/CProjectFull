package com.deshand.adc.dao.api;

import java.sql.Date;
import java.util.List;

import com.deshand.adc.datamodel.Report;

public interface IReportDao {

    Report get(Integer id);

    Report insert(Report report);

    Report update(Report report);

    void delete(Integer id);

	Report getSingleShiftReport(Integer lineId, Date reportDate, Integer shift);

	List<Report> getShiftsByLineAndDate(Integer lineId, Date reportDate);
}

