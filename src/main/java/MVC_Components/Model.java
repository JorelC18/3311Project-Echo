package MVC_Components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Query.QueryInterface;

/**
 * Model part of MVC architecture. It represents data that is being transferred from View and Controller parts.
 * The data being transferred is the SQL data.
 * @author Jorel Louie Chim
 */

public class Model {
	
	// Instance variables for a SQL connection and query
	private Connection connection;
	private ResultSet rs;
	
	public static String url;
	public static String username;
	public static String password;
	
	/**
	 * Gets the connection url for a database.
	 * @param connection the connection url for the database
	 */
	
	private Model(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Creates a connection with a database.
	 * @param url the connection url for the database
	 * @param username the username needed to access the database
	 * @param password the password needed to access the database
	 * @return a model with the connection established
	 */
	
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
	
	/**
	 * Loads data from the database.
	 * @param query the query sent by the user's selections for data
	 */
	
	public void loadData(QueryInterface query) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query.getQuery());
			this.rs = rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets data from the result set sent by the database.
	 * @return the ResultSet from the database
	 */
	
	public ResultSet getData() {
		return rs;
	}
	
	/**
	 * Gets the connection url for the database.
	 * @return the connection url for the database
	 */
	
	public Connection getConnection() {
		return this.connection;
	}
	
}
