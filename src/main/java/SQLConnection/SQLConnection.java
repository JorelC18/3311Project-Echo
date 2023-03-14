package SQLConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class to create SQL data table.

public class SQLConnection {
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/echodata";
			String user = "root";
			String password = "password";
			String csvPath = "src/echoData.csv";
			int batchSize = 20;
			
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			if (conn != null) {
				System.out.println("Connected");
			}
			
			String createTable = "CREATE TABLE `echodata`.`echodata` (\r\n"
					+ "  `REF_DATE` VARCHAR(255) NULL,\r\n"
					+ "  `GEO` VARCHAR(255) NULL,\r\n"
					+ "  `NHPIs` VARCHAR(255) NULL,\r\n"
					+ "  `VALUE` FLOAT NULL);";
			PreparedStatement s1 = conn.prepareStatement(createTable);
			s1.execute();
			
			String sql = "INSERT INTO echodata (REF_DATE, GEO, NHPIs, VALUE) VALUES "
					+ "(?, ?, ?, ?)";
			PreparedStatement s2 = conn.prepareStatement(sql);
			
			BufferedReader lineReader = new BufferedReader(new FileReader(csvPath));
            String lineText = null;
            int count = 0;
            
            while ((lineText = lineReader.readLine()) != null) {
            	String[] data = lineText.split(",");
            	if (data.length == 4) {
            		String ref_date = data[0];
                    String geo = data[1];      
                    String nhpi = data[2];
                    String value = data[3];
                    
                    s2.setString(1, ref_date);
                    s2.setString(2, geo);
                    s2.setString(3, nhpi);
                    s2.setFloat(4, Float.parseFloat(value));
            	}
            	else {
                    String ref_date = data[0];
                    String geo = data[1];      
                    String nhpi = data[2];
                    
                    s2.setString(1, ref_date);
                    s2.setString(2, geo);
                    s2.setString(3, nhpi);
                    s2.setString(4, null);
            	}

                s2.addBatch();
 
                if (count % batchSize == 0) {
                    s2.executeBatch();
                }
            }
 
            lineReader.close();
            s2.executeBatch();
			
		} catch (Exception e) {
			System.out.println("Exception ::" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
