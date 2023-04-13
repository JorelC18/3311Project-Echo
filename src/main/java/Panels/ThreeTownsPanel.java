package Panels;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Chart.ChartContext;
import Chart.ChartData;
import Controllers.dateErrorChecking;
import MVC_Components.View;

public class ThreeTownsPanel extends GeoPanel {
	
	String[] args = new String[3];
	ChartData chartData;
	
	@Override
    public String getPanelName() {
        return "3 Towns";
    }

	@Override
	public String[] getPreArgs(View view) {
		args[0] = view.getThreeTownList1().getSelectedItem().toString();
		args[1] = view.getThreeTownList2().getSelectedItem().toString();
		args[2] = view.getThreeTownList3().getSelectedItem().toString();
		return args;
	}

	@Override
	public void emptySelectionChecking(View view) {
		if (dateErrorChecking.emptySelectionChecking(args[0])
				|| dateErrorChecking.emptySelectionChecking(args[1])
				|| dateErrorChecking.emptySelectionChecking(args[2])) {
			JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
			return;
		}
		if (dateErrorChecking.emptySelectionChecking(view.getThreeTownList1().getSelectedItem().toString())
				|| dateErrorChecking.emptySelectionChecking(
						view.getThreeTownList2().getSelectedItem().toString())
				|| dateErrorChecking.emptySelectionChecking(
						view.getThreeTownList3().getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"One or more selections are empty / raw data has not been loaded.");
			return;
		}
	}

	@Override
	public ChartData loadChartData(View view, ResultSet rs, String[] args) {
		chartData = new ChartData(rs, view.getThreeTownList1().getSelectedItem().toString(),
				view.getThreeTownList2().getSelectedItem().toString(),
				view.getThreeTownList3().getSelectedItem().toString());
		return chartData;
	}

	@Override
	public void drawChart(ChartContext chartContext, ChartData chartData) {
		chartContext.drawChartFor3Series(chartData);
	}
}
