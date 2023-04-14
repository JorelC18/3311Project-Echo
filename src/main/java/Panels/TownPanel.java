package Panels;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Chart.ChartContext;
import Chart.ChartData;
import Controllers.dateErrorChecking;
import MVC_Components.View;

/**
 * This class represents a panel for displaying 2 towns data.
 * @author Jorel Louie Chim
 *
 */

public class TownPanel extends GeoPanel {
	
	String[] args = new String[2];
	ChartData chartData;
	
	/**
	 * Returns the name of this panel.
	 * @return a string representing the name of this panel
	 */
	
	@Override
    public String getPanelName() {
        return "Town";
    }
	
	/**
	 * Returns the arguments for this panel.
	 * @param view the view that represents the user interface
	 * @return an string array that has the arguments
	 */

	@Override
	public String[] getPreArgs(View view) {
		args[0] = view.getTownList1().getSelectedItem().toString();
		args[1] = view.getTownList2().getSelectedItem().toString();
		return args;
	}
	
	/**
	 * Checks for empty selections and displays a JOptionPane if one or more selections are empty.
	 * @param view the view that represents the user interface
	 */

	@Override
	public void emptySelectionChecking(View view) {
		if (dateErrorChecking.emptySelectionChecking(args[0])
				|| dateErrorChecking.emptySelectionChecking(args[1])) {
			JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
			return;
		}
		if (dateErrorChecking.emptySelectionChecking(view.getTownList1().getSelectedItem().toString())
				|| dateErrorChecking.emptySelectionChecking(view.getTownList2().getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"One or more selections are empty / raw data has not been loaded.");
			return;
		}
	}
	
	/**
	 * Loads chart data for this panel.
	 * @param view the view that represents the user interface
	 * @param rs the result set containing query results
	 * @param args the arguments for this panel
	 * @return a chart data object containing all the data needed to draw a chart
	 */


	@Override
	public ChartData loadChartData(View view, ResultSet rs, String[] args) {
		chartData = new ChartData(rs, view.getTownList1().getSelectedItem().toString(),
				view.getTownList2().getSelectedItem().toString());
		return chartData;
	}

	/**
	 * Draws a chart for this panel using the given ChartContext and chartData.
	 * @param chartContext the chart context being used
	 * @param chartData the chart data for the chart being created
	 */

	
	@Override
	public void drawChart(ChartContext chartContext, ChartData chartData) {
		chartContext.drawChartFor2Series(chartData);
	}
}
