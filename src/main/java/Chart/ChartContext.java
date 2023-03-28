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
	 * @param result the ResultSet containing the data for the chart
	 * @param selection1 the first series selection
	 * @param selection2 the second series selection
	 */
	
	public void drawChartFor2Series(ResultSet result, String selection1, String selection2) {
		chartStrategy.drawChartFor2Series(result, selection1, selection2);
	}
	
	/**
	 * Executes the drawChartFor3Series method of the selected strategy.
	 * @param result the ResultSet containing the data for the chart
	 * @param selection1 the first series selection
	 * @param selection2 the second series selection
	 * @param selection3 the third series selection
	 */
	
	public void drawChartFor3Series(ResultSet result, String selection1, String selection2, String selection3) {
		chartStrategy.drawChartFor3Series(result, selection1, selection2, selection3);
	}
	
}
