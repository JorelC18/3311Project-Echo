package Panels;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Chart.ChartContext;
import Chart.ChartData;
import Controllers.dateErrorChecking;
import MVC_Components.View;

public class ProvincePanel extends GeoPanel {
	
	String[] args = new String[2];
	ChartData chartData;
	
	@Override
	public String getPanelName() {
        return "Province";
    }

	@Override
	public String[] getPreArgs(View view) {
		args[0] = view.getProvinceList1().getSelectedItem().toString();
		args[1] = view.getProvinceList2().getSelectedItem().toString();
		return args;
	}

	@Override
	public void emptySelectionChecking(View view) {
		if (dateErrorChecking.emptySelectionChecking(args[0])
				|| dateErrorChecking.emptySelectionChecking(args[1])) {
			JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
			return;
		}
		if (dateErrorChecking.emptySelectionChecking(view.getProvinceList1().getSelectedItem().toString())
				|| dateErrorChecking.emptySelectionChecking(
						view.getProvinceList2().getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"One or more selections are empty / raw data has not been loaded.");
			return;
		}
	}

	@Override
	public ChartData loadChartData(View view, ResultSet rs, String[] args) {
		chartData = new ChartData(rs, view.getProvinceList1().getSelectedItem().toString(),
									view.getProvinceList2().getSelectedItem().toString());
		return chartData;
	}

	@Override
	public void drawChart(ChartContext chartContext, ChartData chartData) {
		chartContext.drawChartFor2Series(chartData);
	}

	
	
}
