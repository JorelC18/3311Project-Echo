package Forecasting;

/**
 * This class sets the strategy for forecasting algorithms, and executing methods based on the strategy selected.
 * @author Jorel Louie Chim
 */

public class ForecastingContext {
	
	private ForecastingStrategy forecastingStrategy;
	
	/**
	 * Sets the strategy for which forecasting algorithm to use.
	 * @param forecastingStrategy the strategy for using forecasting algorithms
	 */
	
	public void setForecastingStrategy(ForecastingStrategy forecastingStrategy) {
		this.forecastingStrategy = forecastingStrategy;
	}
	
	/**
	 * Executes the LinearRegressionForecasting method of the selected strategy.
	 * @param selection1 the first selection location made by the user
	 * @param query the query needed to be made to the database
	 */
	
	public void LinearRegressionForecasting(final String selection1, final String query) {
		forecastingStrategy.LinearRegressionForecasting(selection1, query);
	}
}
