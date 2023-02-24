package NHPIVisualizer;

import java.sql.ResultSet;

public class TestContext {
	
	private TestStrategy testStrategy;
	
	public void setTestStrategy(TestStrategy testStrategy) {
		this.testStrategy = testStrategy;
	}
	
	public void t_Test(ResultSet result, String selection1, String selection2, String endDate, String startDate) {
		testStrategy.t_Test(result, selection1, selection2, endDate, startDate);
    }
    

}
