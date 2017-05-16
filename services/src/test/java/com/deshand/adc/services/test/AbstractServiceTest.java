package com.deshand.adc.services.test;


import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:services-context-test.xml")
abstract class AbstractServiceTest {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceTest.class);
	
	protected void drawLine(){
		LOGGER.info("========================================================================================");
	}
	
	
}
