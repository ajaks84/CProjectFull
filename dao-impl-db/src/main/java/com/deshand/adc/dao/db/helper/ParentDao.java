package com.deshand.adc.dao.db.helper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ParentDao {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ParentDao.class);

	@Inject
	protected JdbcTemplate jdbcTemplate;

	public <T> T get(Integer id, T model) {

		String name = getEntityName(model);

		Class cls = getEntityClassName(model);

		try {
			return jdbcTemplate.queryForObject("select * from " + name + " where id = ? ", new Object[] { id },
					new BeanPropertyRowMapper<T>(cls));
		} catch (Exception e) {
			LOGGER.error("Get " + name + " by ID " + id + " - ERROR");
		}
		return null;
	}

	public <T> List<T> getAll(T model) {
		String name = getEntityName(model);
		Class cls = getEntityClassName(model);
		List<T> rs = jdbcTemplate.query("select * from " + name, new BeanPropertyRowMapper<T>(cls));
		return rs;
	}

	public <T> T update(T model, Integer id) {

		final String INSERT_SQL = buildUpdateQueryStr(model, id);

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {});
				// setParams(ps,material);
				return ps;
			}
		});

		LOGGER.info(getEntityName(model) + " entry with id = " + id + " has been updated");

		return model;
	}

	public <T> void delete(Integer id, T model) {
		String name = getEntityName(model);
		jdbcTemplate.update("delete from " + name + " where id=" + id);
		LOGGER.info(name + " entry with id = " + id + " has been deleted from DB");
	}

	// private PreparedStatement setParams(PreparedStatement ps, Material
	// material) throws SQLException {
	// ps.setString(1, material.getName());
	// ps.setString(2, material.getUnit());
	// ps.setBoolean(3, material.getDeleted());
	// return ps;
	// }

//	public <T> void convert(T model)
//			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
//		// PreparedStatement ps = null;
//		// Field[] fields = model.getClass().getDeclaredFields();
//		Method[] methods = model.getClass().getDeclaredMethods();
//		List<Method> getMethods = new ArrayList();
//		for (Method item : methods) {
//			if (item.getName().startsWith("get") && (item.getName() != "getId")) {
//				// LOGGER.info("item startswith get: "+item.getName());
//				getMethods.add(item);
//			}
//		}
//		// Collections.sort(methods);
//		// getMethods.sort(null);
//		for (Method item : getMethods) {
//			LOGGER.info("item starts with get: " + item.getName());
//		}
//		for (int i = 0; i < getMethods.size(); i++) {
//			// System.out.println(i);
//			// LOGGER.info(getMethods.get(i).getReturnType().getSimpleName().toString());
//			if (getMethods.get(i).getReturnType().getSimpleName().equals("String")) {
//				// LOGGER.info("about to invoke");
//				System.out.println(i);
//				// ps.setString(1, (String) methods[i].invoke(model, null));
//				getMethods.get(i).invoke(model, null);
//			} else if (getMethods.get(i).getReturnType().getSimpleName().equals("Boolean")) {
//
//				System.out.println(i);
//				// ps.setBoolean(1, (Boolean) methods[i].invoke(model, null));
//				getMethods.get(i).invoke(model, null);
//			} else if (getMethods.get(i).getReturnType().getSimpleName().equals("Integer")) {
//				System.out.println(i);
//				// ps.setBoolean(1, (Boolean) methods[i].invoke(model, null));
//				getMethods.get(i).invoke(model, null);
//			}
//		}

		// for (Method item : methods) {
		// if(item.getName().startsWith("get")){
		// System.out.println(item.getReturnType().toString());
		//
		// switch(item.getReturnType().getSimpleName()) {
		// case "String":
		// ps.setString(1, (String) item.invoke(model, null));;
		// break;
		//
		// }
		//
		//
		// LOGGER.info(item.getName());}
		//
		// }
//	}

	protected <T> String getEntityName(T model) {
		return model.getClass().getSimpleName();

	}

	private <T> Class<? extends Object> getEntityClassName(T model) {
		return model.getClass();

	}

	public <T> String buildUpdateQueryStr(T model, Integer id) {

		StringBuilder sqlString = new StringBuilder("update ");

		Field[] fields = model.getClass().getDeclaredFields();

		List<Field> names = Arrays.asList(fields);

		String entityName = getEntityName(model);

		sqlString.append(entityName);
		sqlString.append(" set ");
		String prefix = "";
		for (Field item : names) {
			if (item.getName() != "id") {
				sqlString.append(prefix);
				prefix = ", ";
				if (item.getName().endsWith("Id")) {
					String itm = item.getName().toString();
					String field = itm.substring(0, itm.length() - 2);
					field += "_id";
					sqlString.append(field+"=?");
				} else
					sqlString.append(item.getName() + "=? ");
			}
		}
		sqlString.append(" where id= " + id);
		LOGGER.info(sqlString.toString());
		return sqlString.toString();
	}

}
