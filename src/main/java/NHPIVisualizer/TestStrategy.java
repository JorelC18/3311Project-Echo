package NHPIVisualizer;

import java.sql.ResultSet;

public interface TestStrategy {
	void t_Test(ResultSet result, String selection1, String selection2, String endDate, String startDate);
}
