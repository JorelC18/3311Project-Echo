package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import Chart.BarChartStrategy;
import Chart.ChartContext;
import Chart.ChartData;
import Chart.LineChartStrategy;
import MVC_Components.Model;
import MVC_Components.View;
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
				String[] args = new String[3];
				String startDate = "";
				String endDate = "";
				ChartData chartData;

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

				ChartContext chartContext = new ChartContext();
				model.loadData(query);
				ResultSet rs = model.getData();

				// Date Error Checking:

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

				// End of Date Error Checking

				if (view.getChartTypesComboBox().getSelectedItem().equals("Line Chart")) {

					chartContext.setChartStrategy(new LineChartStrategy());

					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")
							|| view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {

						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {

							if (dateErrorChecking.emptySelectionChecking(view.getProvinceList1().getSelectedItem().toString())
									|| dateErrorChecking.emptySelectionChecking(
											view.getProvinceList2().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(),
										"One or more selections are empty / raw data has not been loaded.");
								return;
							}

							chartData = new ChartData(rs, view.getProvinceList1().getSelectedItem().toString(),
									view.getProvinceList2().getSelectedItem().toString());

						} else {

							if (dateErrorChecking.emptySelectionChecking(view.getTownList1().getSelectedItem().toString())
									|| dateErrorChecking.emptySelectionChecking(view.getTownList2().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(),
										"One or more selections are empty / raw data has not been loaded.");
								return;
							}
							chartData = new ChartData(rs, view.getTownList1().getSelectedItem().toString(),
									view.getTownList2().getSelectedItem().toString());
						}
						
						chartContext.drawChartFor2Series(chartData);

					} else {

						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces")) {

							if (dateErrorChecking.emptySelectionChecking(view.getThreeProvinceList1().getSelectedItem().toString())
									|| dateErrorChecking.emptySelectionChecking(
											view.getThreeProvinceList2().getSelectedItem().toString())
									|| dateErrorChecking.emptySelectionChecking(
											view.getThreeProvinceList3().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(),
										"One or more selections are empty / raw data has not been loaded.");
								return;
							}

							chartData = new ChartData(rs, view.getThreeProvinceList1().getSelectedItem().toString(),
									view.getThreeProvinceList2().getSelectedItem().toString(),
									view.getThreeProvinceList3().getSelectedItem().toString());

						} else {

							if (dateErrorChecking.emptySelectionChecking(view.getThreeTownList1().getSelectedItem().toString())
									|| dateErrorChecking.emptySelectionChecking(
											view.getThreeTownList2().getSelectedItem().toString())
									|| dateErrorChecking.emptySelectionChecking(
											view.getThreeTownList3().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(),
										"One or more selections are empty / raw data has not been loaded.");
								return;
							}

							chartData = new ChartData(rs, view.getThreeTownList1().getSelectedItem().toString(),
									view.getThreeTownList2().getSelectedItem().toString(),
									view.getThreeTownList3().getSelectedItem().toString());
						}
						
						chartContext.drawChartFor3Series(chartData);

					}

				} else {

					chartContext.setChartStrategy(new BarChartStrategy());
					
					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces") || 
							view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {
						
						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
							chartData = new ChartData(rs, view.getProvinceList1().getSelectedItem().toString(),
									view.getProvinceList2().getSelectedItem().toString());
						} else {
							chartData = new ChartData(rs, view.getTownList1().getSelectedItem().toString(),
									view.getTownList2().getSelectedItem().toString());
						}
						
						chartContext.drawChartFor2Series(chartData);
						
					} else {
						
						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces")) {
							chartData = new ChartData(rs, view.getThreeProvinceList1().getSelectedItem().toString(),
									view.getThreeProvinceList2().getSelectedItem().toString(),
									view.getThreeProvinceList3().getSelectedItem().toString());
						} else {
							chartData = new ChartData(rs, view.getThreeTownList1().getSelectedItem().toString(),
									view.getThreeTownList2().getSelectedItem().toString(),
									view.getThreeTownList3().getSelectedItem().toString());
						}
						
						chartContext.drawChartFor3Series(chartData);
						
					}

				}

			}
		});
	}
}
