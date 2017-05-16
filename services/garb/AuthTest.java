package com.deshand.adc.services.test;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.deshand.adc.services.impl.AuthService;

public class AuthTest  extends AbstractServiceTest{
	
	@Inject
	private AuthService service;
	
	@Test
	public void testAuthMethod() {
		Assert.notNull(service.login("alex", "123456"),"login failed");
	}

}
