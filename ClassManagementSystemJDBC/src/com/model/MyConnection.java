package com.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	private MyConnection() {
		
	}
	public static Connection connection = null;


	public static Connection myConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ClassManagementSystem", "root", "root");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return connection;
	}
}
