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

public class chartController extends Controller {
	public chartController(View view, Model model) {
		super(view, model);
	}

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
				String[] args = null;
				ChartData chartData;
				
				Controller parentController = new chartController(view, model);
				parentController.setupTimeGranularityMap();
				parentController.setupGeoComboBoxMap();
				
				ChartContext chartContext = new ChartContext();
				model.loadData(parentController.query);
				ResultSet rs = model.getData();
				
				if (view.getChartTypesComboBox().getSelectedItem().equals("Line Chart")) {
					chartContext.setChartStrategy(new LineChartStrategy());
				} else {
					chartContext.setChartStrategy(new BarChartStrategy());
				}
				
				chartData = parentController.geoComboBoxMap.get(parentController.geoComboBoxSelection).loadChartData(view, rs, args);
				parentController.geoComboBoxMap.get(parentController.geoComboBoxSelection).drawChart(chartContext, chartData);
			}
		});
	}
}

