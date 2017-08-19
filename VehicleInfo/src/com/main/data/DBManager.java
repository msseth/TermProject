package com.main.data;
import java.sql.*;

public class DBManager {
	
	private String user;
	private String password;
	private String url;
	private String className;
	private Connection connection;
	
	
	
	public DBManager(String user, String password, String url, String className) throws ClassNotFoundException, SQLException {
		super();
		try
		{
		
		this.user = user;
		this.password = password;
		this.url = url;
		this.className = className;
		Class.forName(className);
		this.connection=DriverManager.getConnection(url,user,password);
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		
		//this.connection = connection;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	

}
