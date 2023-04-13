package Panels;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Chart.ChartContext;
import Chart.ChartData;
import Controllers.dateErrorChecking;
import MVC_Components.View;

public class TownPanel extends GeoPanel {
	
	String[] args = new String[2];
	ChartData chartData;
	
	@Override
    public String getPanelName() {
        return "Town";
    }

	@Override
	public String[] getPreArgs(View view) {
		args[0] = view.getTownList1().getSelectedItem().toString();
		args[1] = view.getTownList2().getSelectedItem().toString();
		return args;
	}

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

	@Override
	public ChartData loadChartData(View view, ResultSet rs, String[] args) {
		chartData = new ChartData(rs, view.getTownList1().getSelectedItem().toString(),
				view.getTownList2().getSelectedItem().toString());
		return chartData;
	}

	@Override
	public void drawChart(ChartContext chartContext, ChartData chartData) {
		chartContext.drawChartFor2Series(chartData);
	}
}
