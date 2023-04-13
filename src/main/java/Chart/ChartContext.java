package Chart;

import java.sql.ResultSet;

/**
 * This class sets the strategy for drawing charts, and executing methods based on the strategy selected.
 * @author Jorel Louie Chim
 */

public class ChartContext {
	
	private ChartStrategy chartStrategy;
	
	/**
	 * Sets the strategy for drawing charts.
	 * @param chartStrategy the strategy for drawing charts
	 */
	
	public void setChartStrategy(ChartStrategy chartStrategy) {
		this.chartStrategy = chartStrategy;
	}
	
	/**
	 * Executes the drawChartFor2Series method of the selected strategy.
	 * @param chartData a chart data object containing the result set and selections
	 */
	
	public void drawChartFor2Series(ChartData chartData) {
		chartStrategy.drawChartFor2Series(chartData);
	}
	
	/**
	 * Executes the drawChartFor3Series method of the selected strategy.
	 * @param chartData a chart data object containing the result set and selections
	 */
	
	public void drawChartFor3Series(ChartData chartData) {
		chartStrategy.drawChartFor3Series(chartData);
	}
	
}