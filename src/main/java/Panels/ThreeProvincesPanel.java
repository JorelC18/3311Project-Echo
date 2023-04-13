package Panels;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Chart.ChartContext;
import Chart.ChartData;
import Controllers.dateErrorChecking;
import MVC_Components.View;

public class ThreeProvincesPanel extends GeoPanel {
	
	String[] args = new String[3];
	ChartData chartData;
	
	@Override
    public String getPanelName() {
        return "3 Provinces";
    }

	@Override
	public String[] getPreArgs(View view) {
		args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
		args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
		args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
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
		if (dateErrorChecking.emptySelectionChecking(view.getThreeProvinceList1().getSelectedItem().toString())
				|| dateErrorChecking.emptySelectionChecking(
						view.getThreeProvinceList2().getSelectedItem().toString())
				|| dateErrorChecking.emptySelectionChecking(
						view.getThreeProvinceList3().getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"One or more selections are empty / raw data has not been loaded.");
			return;
		}
	}

	@Override
	public ChartData loadChartData(View view, ResultSet rs, String[] args) {
		chartData = new ChartData(rs, view.getThreeProvinceList1().getSelectedItem().toString(),
				view.getThreeProvinceList2().getSelectedItem().toString(),
				view.getThreeProvinceList3().getSelectedItem().toString());
		return chartData;
	}

	@Override
	public void drawChart(ChartContext chartContext, ChartData chartData) {
		chartContext.drawChartFor3Series(chartData);
	}
}
