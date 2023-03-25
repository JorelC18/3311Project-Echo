package Test;

import java.sql.ResultSet;

public class TestContext {
	
	private TestStrategy testStrategy;
	
	public void setTestStrategy(TestStrategy testStrategy) {
		this.testStrategy = testStrategy;
	}
	
	public void t_Test(ResultSet result, String query1, String query2) {
		testStrategy.t_Test(result, query1, query2);
    }
    

}
