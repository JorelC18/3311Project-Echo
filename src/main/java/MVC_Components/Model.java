package MVC_Components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Query.Query;

/*
 * Model part of MVC architecture.
 * Model - represents data that is being transferred from View and Controller parts.
 * The data being transferred is the SQL data.
 */

public class Model {
	
	// Instance variables for a SQL connection and query
	private Connection connection;
	private ResultSet rs;
	//private String query;
	
	public static String url;
	public static String username;
	public static String password;
	
	private Model(Connection connection) {
		this.connection = connection;
	}
	
	// To initialize connection to database
	public static Model createConnection(String url, String username, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			Model.url = url;
			Model.username = username;
			Model.password = password;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Model(conn);
	}
	
	// Load data from database
	public void loadData(Query query) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query.getQuery());
			this.rs = rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Getter method for getting data from rs (ResultSet)
	public ResultSet getData() {
		return rs;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
}
