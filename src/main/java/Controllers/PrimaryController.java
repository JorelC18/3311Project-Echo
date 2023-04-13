package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;

import MVC_Components.Model;
import MVC_Components.View;
import Panels.BothMonthlyAndYearlyPanel;
import Panels.ComboBoxPanel;
import Panels.MonthlyPanel;
import Panels.ProvincePanel;
import Panels.ThreeProvincesPanel;
import Panels.ThreeTownsPanel;
import Panels.TownPanel;
import Panels.YearlyPanel;
import Query.QueryInterface;

/**
 * Controller part of MVC architecture.
 * It processes all logic and requests. works with both view and model.
 * @author Jorel Louie Chim
 */

public class PrimaryController {
	
	String geoComboBoxSelection;
	
	/**
	 * Constructor for the controller part.
	 * @param view view part of the MVC architecture
	 * @param model model part of the MVC architecture
	 */
	
	public PrimaryController(final View view, final Model model) {
		
		// Change drop down menus based on geographical parameter selection.
		
		JComboBox<String> geographicalParametersComboBox = view.getGeographicalParametersComboBox();
		
		/**
		 * Adds an action listener to the geographical parameters combo box to show different combo boxes depending on the user's selection.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		geographicalParametersComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				HashMap<String, ComboBoxPanel> geographicalParametersPanelMap = new HashMap<String, ComboBoxPanel>();
				geographicalParametersPanelMap.put("2 Provinces", new ProvincePanel());
			    geographicalParametersPanelMap.put("2 Towns", new TownPanel());
			    geographicalParametersPanelMap.put("3 Provinces", new ThreeProvincesPanel());
			    geographicalParametersPanelMap.put("3 Towns", new ThreeTownsPanel());
			    view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), geographicalParametersPanelMap.get(geoComboBoxSelection).getPanelName());
			}
		});
		
		// Change drop down menus based on time granularity parameter selection.
		
		JComboBox<String> timeGranularityComboBox = view.getTimeGranularityComboBox();
		
		/**
		 * Adds an action listener to the time granularity combo box to show different combo boxes depending on the user's selection.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		timeGranularityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				HashMap<String, ComboBoxPanel> geographicalParametersPanelMap = new HashMap<String, ComboBoxPanel>();
				geographicalParametersPanelMap.put("Both Monthly and Yearly", new BothMonthlyAndYearlyPanel());
			    geographicalParametersPanelMap.put("Monthly", new MonthlyPanel());
			    geographicalParametersPanelMap.put("Yearly", new YearlyPanel());
			    view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), geographicalParametersPanelMap.get(timeComboBoxSelection).getPanelName());
			}
		});
		
		// Process raw data.
		rawDataController rawDataController = new rawDataController();
		rawDataController.processRawData(view, model);
		
		// Process summary data.
		summaryDataController summaryDataController = new summaryDataController();
		summaryDataController.processSummaryData(view, model);
		
		// Process chart data.
		chartController chartController = new chartController();
		chartController.processCharts(view, model);
		
		// T-Testing
		testController testController = new testController();
		testController.processTest(view, model);
		
		// Forecasting
		forecastingController forecastingController = new forecastingController();
		forecastingController.processForecasting(view, model);
	}
}