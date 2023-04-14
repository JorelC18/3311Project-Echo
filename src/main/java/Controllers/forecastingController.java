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

public class forecastingController extends Controller {
	public forecastingController(View view, Model model) {
		super(view, model);
	}

	public void processForecasting(final View view, final Model model) {		
		JButton loadForecastingButton = view.getLoadForecastingButton();
		
		/**
		 * Adds an action listener to the load forecasting button to start a forecasting algorithm.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadForecastingButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] regInput = new String[1];
				String regQuery = "";

				Controller parentController = new forecastingController(view, model);
				parentController.setupTimeGranularityMap();
				parentController.setupGeoComboBoxMap();
				
				query = QueryFactory.createQuery(parentController.geoComboBoxSelection, parentController.args, parentController.startDate, parentController.endDate);
				
				ForecastingContext forecastingContext = new ForecastingContext();
				forecastingContext.setForecastingStrategy(new LinearRegressionStrategy());
				
				if (parentController.geoComboBoxSelection.equals("3 Provinces") || parentController.geoComboBoxSelection.equals("3 Towns")) {
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns.");
					return;
				}
					
				regInput[0] = parentController.args[0];
				regQuery = QueryFactory.createQuery("Forecasting", regInput, parentController.startDate, parentController.endDate).getQuery();
				forecastingContext.LinearRegressionForecasting(regInput[0], regQuery);
			}
		});
	}
}
