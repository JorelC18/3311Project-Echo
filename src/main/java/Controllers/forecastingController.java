package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Forecasting.ForecastingContext;
import Forecasting.LinearRegressionStrategy;
import MVC_Components.Model;
import MVC_Components.View;
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
				
				ForecastingContext forecastingContext = new ForecastingContext();
				forecastingContext.setForecastingStrategy(new LinearRegressionStrategy());
				
				if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
				
					String[] provinceInput = new String[1];
					provinceInput[0] = view.getProvinceList1().getSelectedItem().toString();
					String provinceQuery = QueryFactory.createQuery("Forecasting", provinceInput, startDate, endDate).getQuery();;
					
					forecastingContext.LinearRegressionForecasting(provinceInput[0], provinceQuery);
					
				}
				else if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {
					String[] townInput = new String[1];
					townInput[0] = view.getTownList1().getSelectedItem().toString();
					String townQuery = QueryFactory.createQuery("Forecasting", townInput, startDate, endDate).getQuery();
					
					forecastingContext.LinearRegressionForecasting(townInput[0], townQuery);
				}
				else {
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns.");
					return;
				}
			}
		});
	}
}
