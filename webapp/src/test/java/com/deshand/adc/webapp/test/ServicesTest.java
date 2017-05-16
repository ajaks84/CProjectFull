package com.deshand.adc.webapp.test;

import java.sql.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deshand.adc.datamodel.Report;
import com.deshand.adc.services.services.ILineService;
import com.deshand.adc.services.services.IReportService;

public class ServicesTest {

	private static ClassPathXmlApplicationContext context;

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("web-application-context.xml");

		IReportService service = context.getBean(IReportService.class);
		ILineService serviceLine = context.getBean(ILineService.class);

		//System.out.println(serviceLine.get(2));
		//System.out.println(service.get(2));

//		Line line = new Line();
//		line.setName("LZ-04");
//		line.setProductId(2);
//		line.setSpeed(400);
//		line.setUnit("can/min");
//		line.setCapacity(300000);
//		line.setBuildYear(1974);
//		line.setFactoryId(1);

		// System.out.println(service.get(1));
		// System.out.println(service.getAll());
		//System.out.println(new Time(new java.util.Date().getTime()));
		Report report = new Report();
		report.setInput(77854);
		report.setDate(new Date(new java.util.Date().getTime()));
		report.setShift(2);
		report.setRegister(50023);
		report.setOutput(74332);
		report.setLineId(1);
		service.save(report);

		// System.out.println("Saved report:" + report);
		// System.out.println(service.get(6));
		//
		// System.out.println(service.get(report.getId()));
		// service.delete(report.getId());
		// System.out.println(service.get(report.getId()));
	}
}
