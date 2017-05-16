package com.deshand.adc.webapp.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deshand.adc.services.services.ILineService;


public class TransactionsTest {

    private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("web-application-context.xml");

        @SuppressWarnings("unused")
		ILineService service = context.getBean(ILineService.class);

//        Report report = new Report();
//		report.setInput(77854);
//		report.setDate(new Date());
//		report.setShift(2);
//		report.setOrder(50023);
//		report.setOutput(74332);
//		report.setLineId(1);
//
//		Report report2 = new Report();
//		report2.setInput(77854);
//		report2.setDate(new Date());
//		report2.setShift(-2);
//		report2.setOrder(50023);
//		report2.setOutput(74332);
//		report2.setLineId(1);
        
//        Line line = new Line();
//		line.setName("LZ-04");
//		line.setProductId(2);
//		line.setSpeed(400);
//		line.setUnit("can/min");
//		line.setCapacity(300000);
//		line.setBuildYear(1974);
//		line.setFactoryId(1);
//		
//		 Line line2 = new Line();
//			line2.setName("LZ-04");
//			line2.setProductId(2);
//			line2.setSpeed(400);
//			line2.setUnit("can/min");
//			line2.setCapacity(300000);
//			line2.setBuildYear(1974);
//			line2.setFactoryId(1);
//		
//        service.saveMultiple(line, line2);

    }
}
