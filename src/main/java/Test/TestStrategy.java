package Test;

import java.sql.ResultSet;

/**
 * This is an interface for the t-test stategies.
 * @author Jorel Louie Chim
 */

public interface TestStrategy {
	
	/**
	 * Performs a t-test.
	 * @param result the result set containing the data needed for the t-test
	 * @param query1 the first query needed for the t-test
	 * @param query2 the second query needed for the t-test
	 */
	
	void t_Test(ResultSet result, String query1, String query2);
}
