package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import MVC_Components.Model;
import MVC_Components.View;
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
				String[] args = new String[3];
				String startDate = "";
				String endDate = "";

				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-"
							+ view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-"
							+ view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!dateErrorChecking.fullDateErrorChecking(startDate, endDate)) {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDate = selectedYear + "-" + startMonth;
					endDate = selectedYear + "-" + endMonth;
					if (!dateErrorChecking.partialDateErrorChecking(startMonth, endMonth)) {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDate = startYear + "-01";
					endDate = endYear + "-12";
					if (!dateErrorChecking.partialDateErrorChecking(startYear, endYear)) {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}

				if (geoComboBoxSelection.equals("2 Provinces")) {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0])
							|| dateErrorChecking.emptySelectionChecking(args[1])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("2 Provinces", args, startDate, endDate);
				} 
				else if (geoComboBoxSelection.equals("2 Towns")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0])
							|| dateErrorChecking.emptySelectionChecking(args[1])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("2 Towns", args, startDate, endDate);
				} 
				else if (geoComboBoxSelection.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0])
							|| dateErrorChecking.emptySelectionChecking(args[1])
							|| dateErrorChecking.emptySelectionChecking(args[2])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("3 Provinces", args, startDate, endDate);
				} 
				else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0])
							|| dateErrorChecking.emptySelectionChecking(args[1])
							|| dateErrorChecking.emptySelectionChecking(args[2])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("3 Towns", args, startDate, endDate);
				}
				
				TestContext testContext = new TestContext();
				model.loadData(query);
				ResultSet rs = model.getData();
				
				// Date Error Checking:
				
				String startDateCheck = "";
				String endDateCheck = "";
				
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDateCheck = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDateCheck = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!dateErrorChecking.fullDateErrorChecking(startDateCheck, endDateCheck))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDateCheck = selectedYear + "-" + startMonth;
					endDateCheck = selectedYear + "-" + endMonth;
					if (!dateErrorChecking.partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDateCheck = startYear + "-01";
					endDateCheck = endYear + "-12";
					if (!dateErrorChecking.partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				// End of Date Error Checking
				
				testContext.setTestStrategy(new t_TestStrategy());
				
				// Display error message when either 3 provinces or 3 towns is selected
				if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces") 
						|| view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Towns")) {
					
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns");
					
				}
				else {
					// Check if either of the two province inputs are empty
					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
						if (dateErrorChecking.emptySelectionChecking(view.getProvinceList1().getSelectedItem().toString()) 
								|| dateErrorChecking.emptySelectionChecking(view.getProvinceList2().getSelectedItem().toString())) {
							
							JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / test can not perform.");
							return;
						}
						
						String[] provinceInput = new String[1];
						provinceInput[0] = view.getProvinceList1().getSelectedItem().toString();
						String query1 = QueryFactory.createQuery("t_Test", provinceInput, startDate, endDate).getQuery();
						
						String[] provinceInput2 = new String[1];
						provinceInput2[0] = view.getProvinceList2().getSelectedItem().toString();
						String query2 = QueryFactory.createQuery("t_Test", provinceInput2, startDate, endDate).getQuery();
						
						testContext.t_Test(rs, query1, query2);
					
						
				}
				else {
						// Check if either of the two town inputs are empty
						if (dateErrorChecking.emptySelectionChecking(view.getTownList1().getSelectedItem().toString()) 
								|| dateErrorChecking.emptySelectionChecking(view.getTownList2().getSelectedItem().toString())) {
							
							JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / test can not perform .");
							return;
						}
						
						String[] townInput = new String[1];
						townInput[0] = view.getTownList1().getSelectedItem().toString();
						String query1 = QueryFactory.createQuery("t_Test", townInput, startDate, endDate).getQuery();
						
						String[] townInput2 = new String[1];
						townInput2[0] = view.getTownList2().getSelectedItem().toString();
						String query2 = QueryFactory.createQuery("t_Test", townInput2, startDate, endDate).getQuery();
						
						testContext.t_Test(rs, query1, query2);
						
					}
				}
				
				
			}
			
		});
	}
}
