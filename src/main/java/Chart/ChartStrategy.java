package Chart;

import java.sql.ResultSet;

/**
 * This is an interface for the chart strategies.
 * @author Jorel Louie Chim
 */

public interface ChartStrategy {
	
	/** Draws a chart with 2 series from a result set, using the specified selections.
	* @param result the result set containing the data for the chart
	* @param selection1 the first selection for the chart
	* @param selection2 the second selection for the chart
	*/
	
	void drawChartFor2Series(ResultSet result, String selection1, String selection2);
	
	/** Draws a chart with 3 series from a result set, using the specified selections.
	* @param result the result set containing the data for the chart
	* @param selection1 the first selection for the chart
	* @param selection2 the second selection for the chart
	* @param selection3 the third selection for the chart
	*/
	
	void drawChartFor3Series(ResultSet result, String selection1, String selection2, String selection3);
}
