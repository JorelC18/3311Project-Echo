package NHPIVisualizer;

import java.sql.Connection;
import java.sql.ResultSet;

/*
 * Model part of MVC architecture.
 * Model - represents data that is being transferred from View and Controller parts.
 * The data being transferred is the SQL data.
 */

public class GUIModel {
	
	// Instance variables for a SQL connection and query
	private Connection connection;
	private ResultSet rs;
	private String query;
	
	// To initialize connection to database
	public GUIModel(String url, String username, String password) {
		
	}
	
	// Load data from database
	public void loadData() {
		
	}
	
	// Getter method for getting data from rs (ResultSet)
	public ResultSet getData() {
		return rs;
	}
	
}
