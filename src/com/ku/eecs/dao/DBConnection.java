package com.ku.eecs.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getDBConnection() throws Exception
	{
		Connection connect = null;
		try
		{
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/eecs746", "eecs746","password");
			if(connect != null)
				return connect;
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		return null;
	}
}
