package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;

import Chart.BarChartStrategy;
import Chart.ChartContext;
import Chart.ChartData;
import Chart.LineChartStrategy;
import MVC_Components.Model;
import MVC_Components.View;
import Panels.BothMonthlyAndYearlyPanel;
import Panels.GeoPanel;
import Panels.MonthlyPanel;
import Panels.ProvincePanel;
import Panels.ThreeProvincesPanel;
import Panels.ThreeTownsPanel;
import Panels.TimePanel;
import Panels.TownPanel;
import Panels.YearlyPanel;
import Query.QueryInterface;
import Query.QueryFactory;

public class chartController {
	private QueryInterface query;

	public void processCharts(final View view, final Model model) {
		JButton loadChartButton = view.getLoadChartButton();

		/**
		 * Adds an action listener to the load chart button to show data loaded into a
		 * chart based on the user's selections.
		 * 
		 * @param e The ActionEvent object that represents the user's action taken
		 */

		loadChartButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				String[] args;
				String startDate = "";
				String endDate = "";
				ChartData chartData;

				HashMap<String, TimePanel> timeGranularityMap = new HashMap<String, TimePanel>();
				timeGranularityMap.put("Both Monthly and Yearly", new BothMonthlyAndYearlyPanel());
				timeGranularityMap.put("Monthly", new MonthlyPanel());
				timeGranularityMap.put("Yearly", new YearlyPanel());
				startDate = timeGranularityMap.get(timeComboBoxSelection).getStartDate(view);
				endDate = timeGranularityMap.get(timeComboBoxSelection).getEndDate(view);
				timeGranularityMap.get(timeComboBoxSelection).dateErrorChecking(view, startDate, endDate);
				
				
				HashMap<String, GeoPanel> geoComboBoxMap = new HashMap<String, GeoPanel>();
				geoComboBoxMap.put("2 Provinces", new ProvincePanel());
			    geoComboBoxMap.put("2 Towns", new TownPanel());
			    geoComboBoxMap.put("3 Provinces", new ThreeProvincesPanel());
			    geoComboBoxMap.put("3 Towns", new ThreeTownsPanel());
			    args = geoComboBoxMap.get(geoComboBoxSelection).getPreArgs(view);
			    geoComboBoxMap.get(geoComboBoxSelection).emptySelectionChecking(view);
				query = QueryFactory.createQuery(geoComboBoxSelection, args, startDate, endDate);

				ChartContext chartContext = new ChartContext();
				model.loadData(query);
				ResultSet rs = model.getData();
				
				if (view.getChartTypesComboBox().getSelectedItem().equals("Line Chart")) {
					chartContext.setChartStrategy(new LineChartStrategy());
				} else {
					chartContext.setChartStrategy(new BarChartStrategy());
				}
				
				chartData = geoComboBoxMap.get(geoComboBoxSelection).loadChartData(view, rs, args);
				geoComboBoxMap.get(geoComboBoxSelection).drawChart(chartContext, chartData);
			}
		});
	}
}

