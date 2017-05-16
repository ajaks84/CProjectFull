package com.deshand.adc.dao.db.auth;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.db.helper.ParentDao;
import com.deshand.adc.dao.db.mapper.UserDataMapper;

@Repository
public class Authentification {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParentDao.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	public UserData login(String userName, String password) {

		String query = "select u.username,u.password, u.enabled, r.rolename "
				+ "from tbl_users u, tbl_user_role r where u.id = r.userid and u.username='" + userName + "'";

		try {
			return jdbcTemplate.queryForObject(query, new UserDataMapper());
		} catch (Exception e) {
			LOGGER.error("Login unsuccessful");
		}
		return null;
	}

	//
	// public static void main(String[] args) {
	//
	// System.out.println(jdbcTemplate);
	//
	// Authentification a = new Authentification();
	// a.login("alex", "123456");
	//
	//
	// }

}
