package Test;

import java.sql.ResultSet;

/**
 * This class sets the strategy for conducting t-tests, and executing methods based on the strategy selected.
 * @author Jorel Louie Chim
 */

public class TestContext {
	
	private TestStrategy testStrategy;
	
	/**
	 * Sets the strategy for conducting t-tests.
	 * @param chartStrategy the strategy for drawing charts
	 */
	
	public void setTestStrategy(TestStrategy testStrategy) {
		this.testStrategy = testStrategy;
	}
	
	/**
	 * Executes the t_Test method of the selected strategy.
	 * @param result the ResultSet containing the data needed for the statistical test
	 * @param query1 the first query needed for the t-test
	 * @param query2 the second query needed for the t-test
	 */
	
	public void t_Test(ResultSet result, String query1, String query2) {
		testStrategy.t_Test(result, query1, query2);
    }
    

}
