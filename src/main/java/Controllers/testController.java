package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;

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
import Test.TestContext;
import Test.t_TestStrategy;

public class testController {
	private QueryInterface query;
	
	public void processTest(final View view, final Model model) {
		
		JButton loadTestButton = view.getLoadTestButton();
		
		/**
		 * Adds an action listener to the load test button to show values resulting from a statistical test based on the user's selections.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadTestButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				String[] args;
				String[] testInput = new String[1];
				String[] testInput2 = new String[1];
				String query1 = "";
				String query2 = "";
				String startDate = "";
				String endDate = "";

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
				
				TestContext testContext = new TestContext();
				model.loadData(query);
				ResultSet rs = model.getData();
				
				
				testContext.setTestStrategy(new t_TestStrategy());
				
				// Display error message when either 3 provinces or 3 towns is selected
				if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces") 
						|| view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Towns")) 
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns");
				
				testInput[0] = args[0];
				testInput2[0] = args[1];
				query1 = QueryFactory.createQuery("t_Test", testInput, startDate, endDate).getQuery();
				query2 = QueryFactory.createQuery("t_Test", testInput2, startDate, endDate).getQuery();
				testContext.t_Test(rs, query1, query2);	
			}
			
		});
	}
}
