package Panels;

import java.sql.ResultSet;

import Chart.ChartContext;
import Chart.ChartData;
import MVC_Components.View;

public abstract class GeoPanel {

	public abstract String getPanelName();
	
	public abstract String[] getPreArgs(final View view);
	
	public abstract void emptySelectionChecking(final View view);
	
	public abstract ChartData loadChartData(final View view, ResultSet rs, String[] args);
	
	public abstract void drawChart(ChartContext chartContext, ChartData chartData);
	
}
