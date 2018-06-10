package yt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String DATABASE = "localhost:3306/webprojekat";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	
	private static Connection connection;
	
	public static void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
//		open();
		return connection;
	}
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
