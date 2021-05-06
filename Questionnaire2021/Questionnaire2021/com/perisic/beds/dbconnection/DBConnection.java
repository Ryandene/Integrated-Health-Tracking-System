package com.perisic.beds.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	static Connection conn = null;
	
	private DBConnection() {
		
	}
	
	public static Connection getDBConnection() {
		try {
			if(conn == null) {
				String url = "jdbc:sqlserver://LAPTOP-JAB2VDM6;Database=LSQuestionDB;integrstedSecurity=true";
				String user = "sa";
				String password = "sv7600";
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection(url,user,password);
				System.out.println("connected");
			}
		}catch(Exception e) {
			System.out.println("db except");
			e.printStackTrace();
		}
		
		return conn;
	}
}
