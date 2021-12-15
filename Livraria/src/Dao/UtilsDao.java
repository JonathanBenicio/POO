/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class UtilsDao {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static Connection getConnection() {
		try {
			Properties connConfig = new Properties();
			connConfig.setProperty("user", "root");
			connConfig.setProperty("password", "jonathan");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/library", connConfig);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
