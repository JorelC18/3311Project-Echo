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

public class testController extends Controller {
	public testController(View view, Model model) {
		super(view, model);
	}

	public void processTest(final View view, final Model model) {
		
		JButton loadTestButton = view.getLoadTestButton();
		
		/**
		 * Adds an action listener to the load test button to show values resulting from a statistical test based on the user's selections.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadTestButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] testInput = new String[1];
				String[] testInput2 = new String[1];
				String query1 = "";
				String query2 = "";

				Controller parentController = new testController(view, model);
				parentController.setupTimeGranularityMap();
				parentController.setupGeoComboBoxMap();
				
				TestContext testContext = new TestContext();
				model.loadData(parentController.query);
				ResultSet rs = model.getData();
				
				testContext.setTestStrategy(new t_TestStrategy());
				
				// Display error message when either 3 provinces or 3 towns is selected
				if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces") 
						|| view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Towns")) 
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns");
				
				testInput[0] = parentController.args[0];
				testInput2[0] = parentController.args[1];
				query1 = QueryFactory.createQuery("t_Test", testInput, parentController.startDate, parentController.endDate).getQuery();
				query2 = QueryFactory.createQuery("t_Test", testInput2, parentController.startDate, parentController.endDate).getQuery();
				testContext.t_Test(rs, query1, query2);	
			}
			
		});
	}
}
