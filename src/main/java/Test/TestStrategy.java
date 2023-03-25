package Test;

import java.sql.ResultSet;

public interface TestStrategy {
	void t_Test(ResultSet result, String query1, String query2);
}
