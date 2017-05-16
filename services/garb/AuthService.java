package com.deshand.adc.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deshand.adc.dao.db.auth.Authentification;
import com.deshand.adc.dao.db.auth.UserData;

@Service
public class AuthService {
	
	@Inject
	private Authentification auth;
	
	public UserData login(String userName, String password) {
		return auth.login(userName, password);
	}

}
