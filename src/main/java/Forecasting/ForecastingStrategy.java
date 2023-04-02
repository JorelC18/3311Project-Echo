package Forecasting;

/**
 * This is an interface for the forecasting strategies.
 * @author Jorel Louie Chim
 */

public interface ForecastingStrategy {
	
	/**
	 * Executes a linear regression algorithm for forecasting. 
	 * @param selection1 the first selection location made by the user
	 * @param query the query needed to be made to the database
	 */
	
	void LinearRegressionForecasting(final String selection1, final String query);
	
}
