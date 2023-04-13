package Chart;

import java.sql.ResultSet;

/**
 * This is an interface for the chart strategies.
 * @author Jorel Louie Chim
 */

public interface ChartStrategy {
	
	/** Draws a chart with 2 series from a result set, using the specified selections.
	* @param chartData a chart data object containing the result set and selections
	*/
	
	void drawChartFor2Series(ChartData chartData);
	
	/** Draws a chart with 3 series from a result set, using the specified selections.
	* @param chartData a chart data object containing the result set and selections
	*/
	
	void drawChartFor3Series(ChartData chartData);
}
