package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class t_TestDataRetrieval {
	/**
	 * retrieves the data from selected location.
	 * @param query the query needed to retrieve the data
	 */
	
	public double[] t_Test_DataRetrieval(String query) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/echodata", "root", "password");
		ResultSet rs;
		ArrayList<Double> selectedData = new ArrayList<Double>();
		Statement statement = connection.prepareStatement(query);
		rs = statement.executeQuery(query);
		while (rs.next()) {
			String inputStr = rs.getString(1);
			Double input = null;
			if (inputStr != null) {
				input = Double.parseDouble(rs.getString(1));
			}
			selectedData.add(input);
		}
		double[] result = new double[selectedData.size()];
		for (int i = 0; i < selectedData.size(); i++) {
			if (selectedData.get(i) != null) {
				result[i] = selectedData.get(i);
			}
		}
		return result;
	}
}
