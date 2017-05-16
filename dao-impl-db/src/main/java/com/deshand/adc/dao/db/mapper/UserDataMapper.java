package com.deshand.adc.dao.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deshand.adc.dao.db.auth.UserData;

public final class UserDataMapper implements RowMapper<UserData> {

	@Override
	public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
		

		UserData uData = new UserData();

		uData.setUserName(rs.getString("username"));
		uData.setPassword(rs.getString("password"));
		uData.setEnabled(rs.getBoolean("enabled"));
		uData.setRoleName(rs.getString("rolename"));

		
		System.out.println(uData);
		
		return uData;
	}

}
