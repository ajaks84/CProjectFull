package com.deshand.adc.dao.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// This is a test class to show how complicated is plain java code is :)

public class DbConnectionTest {
    public static void main(String[] args) {
        Connection con = null;
        try {
            //Class.forName("org.postgresql.Driver");  // Just to check if driver can loaded
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "postgres");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "mysql");
            System.out.println("Connection succesfull!");

            Statement createStatement = con.createStatement();
            createStatement.execute("select * from book order by id");

            ResultSet resultSet = createStatement.getResultSet();
            resultSet.next();
            String name = resultSet.getString("title");
            System.out.println(name);
            con.close();
            //boolean next = 
            resultSet.next();
            int id2 = resultSet.getInt("id");
            //con.close();
            System.out.println(id2);
            
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}
