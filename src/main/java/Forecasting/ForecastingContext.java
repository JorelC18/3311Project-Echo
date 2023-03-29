package Forecasting;

public class ForecastingContext {
	private ForecastingStrategy forecastingStrategy;
	
	public void setForecastingStrategy(ForecastingStrategy forecastingStrategy) {
		this.forecastingStrategy = forecastingStrategy;
	}
	
	public void LinearRegressionForecasting(final String selection1, final String query) {
		forecastingStrategy.LinearRegressionForecasting(selection1, query);
	}
}
