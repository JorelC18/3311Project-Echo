package NHPIVisualizer;

import java.sql.ResultSet;

public class ChartContext {
	
	private ChartStrategy chartStrategy;
	
	public void setChartStrategy(ChartStrategy chartStrategy) {
		this.chartStrategy = chartStrategy;
	}
	
	public void drawChartFor2Series(ResultSet result, String selection1, String selection2) {
		chartStrategy.drawChartFor2Series(result, selection1, selection2);
	}
	
	public void drawChartFor3Series(ResultSet result, String selection1, String selection2, String selection3) {
		chartStrategy.drawChartFor3Series(result, selection1, selection2, selection3);
	}
	
}
