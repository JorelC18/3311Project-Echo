package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import Forecasting.ForecastingContext;
import Forecasting.LinearRegressionStrategy;
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

public class forecastingController {
	private QueryInterface query;
	
	public void processForecasting(final View view, final Model model) {		
		JButton loadForecastingButton = view.getLoadForecastingButton();
		
		/**
		 * Adds an action listener to the load forecasting button to start a forecasting algorithm.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadForecastingButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				String[] args = new String[3];
				String[] regInput = new String[1];
				String startDate = "";
				String endDate = "";
				String regQuery = "";

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
				
				
				ForecastingContext forecastingContext = new ForecastingContext();
				forecastingContext.setForecastingStrategy(new LinearRegressionStrategy());
				
				if (geoComboBoxSelection.equals("3 Provinces") || geoComboBoxSelection.equals("3 Towns")) {
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns.");
					return;
				}
					
				regInput[0] = args[0];
				regQuery = QueryFactory.createQuery("Forecasting", regInput, startDate, endDate).getQuery();
				forecastingContext.LinearRegressionForecasting(regInput[0], regQuery);
			}
		});
	}
}
